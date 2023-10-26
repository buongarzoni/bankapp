package com.bankapp.navigation.graphs.onboarding

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bankapp.navigation.domain.NavRoute
import com.bankapp.onboarding.register.presentation.RegistrationViewModel
import com.bankapp.onboarding.register.view.RegistrationScreen

object RegistrationRoute : NavRoute<RegistrationViewModel> {
    override val route: String = "registration/"

    @Composable
    override fun viewModel(): RegistrationViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: RegistrationViewModel) =
        RegistrationScreen(presenter = viewModel)
}
