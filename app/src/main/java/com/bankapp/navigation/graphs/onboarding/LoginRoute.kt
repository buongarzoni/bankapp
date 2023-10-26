package com.bankapp.navigation.graphs.onboarding

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bankapp.navigation.domain.NavRoute
import com.bankapp.onboarding.login.presentation.LoginViewModel
import com.bankapp.onboarding.login.view.LoginScreen

object LoginRoute: NavRoute<LoginViewModel> {
    override val route: String = "login/"

    @Composable
    override fun viewModel(): LoginViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: LoginViewModel) = LoginScreen(presenter = viewModel)
}
