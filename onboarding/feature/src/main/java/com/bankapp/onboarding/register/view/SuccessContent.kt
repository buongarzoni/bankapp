package com.bankapp.onboarding.register.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bankapp.components.lotties.LottieSuccess
import com.bankapp.components.preview.UIModePreview
import com.bankapp.components.theme.BankappTheme
import com.bankapp.onboarding.R
import com.bankapp.onboarding.register.presentation.RegistrationPresenter
import com.bankapp.onboarding.register.presentation.RegistrationPresenterPreview

@Composable
fun SuccessContent(presenter: RegistrationPresenter) = LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Bottom,
) {
    item {
        Title()
        LottieSuccess(Modifier.size(200.dp))
        ButtonContinue(presenter)
    }
}

@Composable
private fun Title() = Text(
    text = stringResource(id = R.string.onboarding_feature_headline_registration_success),
    style = MaterialTheme.typography.headlineMedium,
)

@Composable
private fun ButtonContinue(presenter: RegistrationPresenter) = Button(
    modifier = Modifier.fillMaxWidth(0.5f),
    onClick = { presenter.confirmSuccessClicked() },
) {
    Text(text = stringResource(id = R.string.onboarding_feature_button_continue))
}

@UIModePreview
@Composable
private fun Preview() = BankappTheme(dynamicColor = false) {
    SuccessContent(presenter = RegistrationPresenterPreview())
}
