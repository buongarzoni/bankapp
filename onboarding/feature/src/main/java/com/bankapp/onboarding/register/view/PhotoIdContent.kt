package com.bankapp.onboarding.register.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddAPhoto
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.bankapp.components.preview.UIModePreview
import com.bankapp.components.theme.BankappTheme
import com.bankapp.onboarding.R
import com.bankapp.onboarding.register.presentation.RegistrationPresenter
import com.bankapp.onboarding.register.presentation.RegistrationPresenterPreview
import com.bankapp.onboarding.utils.AsTextFieldError

@Composable
fun PhotoIdContent(presenter: RegistrationPresenter) {
    ImagePicker(presenter)
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = stringResource(id = R.string.onboarding_feature_headline_take_picture_id),
                style = MaterialTheme.typography.headlineSmall,
            )
            ImagePlaceholder(presenter)
            Spacer(Modifier.height(20.dp))
            Buttons(presenter)
        }
    }
}

@Composable
private fun ImagePicker(presenter: RegistrationPresenter) {
    if (presenter.showImageSourceDialog.value) {
        AskImageSourceDialog(
            availableUri = presenter.availableUri.value,
            onDismiss = { presenter.dismissImageSourceDialog() },
            onUriLoaded = { presenter.onUriLoaded(it) },
        )
    }
}

@Composable
private fun ImagePlaceholder(presenter: RegistrationPresenter) {
    val uri = presenter.uri.value
    val containerSize = 144.dp
    if (uri == null) {
        Card(
            modifier = Modifier.clickable { presenter.addPictureClicked() },
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Box(
                modifier = Modifier.size(containerSize),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier.size(containerSize / 2),
                    imageVector = Icons.Outlined.AddAPhoto,
                    contentDescription = stringResource(id = R.string.onboarding_feature_cd_add_id_picture),
                    tint = LocalContentColor.current.copy(alpha = 0.5f),
                )
            }
        }
    } else {
        Image(
            modifier = Modifier
                .size(containerSize)
                .clickable { presenter.addPictureClicked() },
            painter = rememberAsyncImagePainter(uri),
            contentDescription = stringResource(id = R.string.onboarding_feature_cd_id_picture),
        )
    }
    ImageError(presenter)
}

@Composable
private fun Buttons(presenter: RegistrationPresenter) = Row {
    BackButton(modifier = Modifier.weight(1f), presenter = presenter)
    Spacer(Modifier.width(20.dp))
    SubmitButton(modifier = Modifier.weight(1f), presenter = presenter)
}

@Composable
private fun BackButton(
    modifier: Modifier,
    presenter: RegistrationPresenter,
) = OutlinedButton(
    modifier = modifier,
    onClick = { presenter.backClicked() },
) {
    Text(stringResource(R.string.onboarding_feature_button_back))
}

@Composable
private fun SubmitButton(
    modifier: Modifier,
    presenter: RegistrationPresenter,
) = Button(
    modifier = modifier,
    onClick = { presenter.submitClicked() },
) {
    Text(stringResource(R.string.onboarding_feature_button_submit))
}

@Composable
private fun ImageError(presenter: RegistrationPresenter) = presenter.uriError.value?.AsTextFieldError()

@UIModePreview
@Composable
private fun Preview() = BankappTheme(dynamicColor = false) {
    Surface {
        PhotoIdContent(RegistrationPresenterPreview())
    }
}
