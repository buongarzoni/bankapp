package com.bankapp.components.modal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bankapp.components.R
import com.bankapp.components.preview.UIModePreview
import com.bankapp.components.theme.BankappTheme

@Composable
fun LoadingModal(
    title: String,
    onDismiss: () -> Unit,
) = BankAppModal(
    onDismiss = { onDismiss() },
) {
    val composition =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_loading))
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center,
    )
    LottieAnimation(
        modifier = Modifier.fillMaxWidth(),
        composition = composition.value,
        iterations = LottieConstants.IterateForever,
    )
}


@Composable
fun ErrorModal(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    text: String,
) = BankAppModal(
    onDismiss = { onDismiss() },
) {
    val composition =
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.lottie_error))
    LottieAnimation(
        modifier = Modifier.size(96.dp),
        composition = composition.value,
    )
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = stringResource(id = R.string.components_headline_error_modal),
        style = MaterialTheme.typography.headlineMedium,
    )
    Text(text = text)
    Spacer(modifier = Modifier.height(20.dp))
    Button(
        modifier = Modifier.fillMaxWidth(0.5f),
        onClick = { onConfirm() },
    ) {
        Text(text = stringResource(id = R.string.components_button_confirm))
    }
}

@Composable
private fun BankAppModal(
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) = Dialog(
    onDismissRequest = { onDismiss() },
) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            content()
        }
    }
}

@UIModePreview
@Composable
private fun PreviewErrorModal() = BankappTheme(dynamicColor = false) {
    ErrorModal(
        onDismiss = { /* ignored */ },
        onConfirm = { /* ignored */ },
        text = "some text",
    )
}
