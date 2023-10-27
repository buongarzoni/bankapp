package com.bankapp.onboarding.register.view

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import com.bankapp.components.preview.UIModePreview
import com.bankapp.components.theme.BankappTheme
import com.bankapp.onboarding.R

@Composable
fun AskImageSourceDialog(
    availableUri: Uri,
    onUriLoaded: (Uri?) -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            GalleryPicker(
                modifier = Modifier.fillMaxWidth(0.5f),
            ) {
                onUriLoaded(it)
            }

            PhotoPicker(
                modifier = Modifier.fillMaxWidth(),
                availableUri = availableUri,
            ) {
                onUriLoaded(it)
            }
        }
    }
}

@Composable
private fun GalleryPicker(
    modifier: Modifier,
    onUriLoaded: (Uri?) -> Unit,
) {
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            onUriLoaded(it)
        }

    DialogOption(
        modifier = modifier,
        imageVector = Icons.Default.Image,
        text = stringResource(id = R.string.onboarding_feature_button_open_gallery),
        imageDescription = stringResource(id = R.string.onboarding_feature_cd_open_gallery),
        onClick = { galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
    )
}

@Composable
private fun PhotoPicker(
    modifier: Modifier,
    availableUri: Uri,
    onUriLoaded: (Uri?) -> Unit,
) {
    val context = LocalContext.current
    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                onUriLoaded(availableUri)
            }
        }

    val launchCamera = { cameraLauncher.launch(availableUri) }
    val cameraPermissionRevokedText =
        stringResource(id = R.string.onboarding_feature_toast_camera_permission_revoked)

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            launchCamera()
        } else {
            Toast.makeText(context, cameraPermissionRevokedText, Toast.LENGTH_SHORT).show()
        }
    }

    DialogOption(
        modifier = modifier,
        imageVector = Icons.Filled.PhotoCamera,
        text = stringResource(id = R.string.onboarding_feature_button_take_photo),
        imageDescription = stringResource(id = R.string.onboarding_feature_cd_take_photo),
        onClick = {
            if (PackageManager.PERMISSION_GRANTED ==
                ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            ) {
                launchCamera()
            } else {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        },
    )
}

@Composable
private fun DialogOption(
    modifier: Modifier,
    imageVector: ImageVector,
    text: String,
    imageDescription: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(96.dp),
            imageVector = imageVector,
            contentDescription = imageDescription,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
        )

        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = text,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@UIModePreview
@Composable
private fun Preview() = BankappTheme(dynamicColor = false) {
    AskImageSourceDialog(
        availableUri = Uri.EMPTY,
        onDismiss = {},
        onUriLoaded = {},
    )
}
