package com.bankapp.navigation.graphs.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bankapp.home.feature.main.presentation.MainViewModel
import com.bankapp.home.feature.main.view.MainScreen
import com.bankapp.navigation.domain.NavRoute

object HomeRoute: NavRoute<MainViewModel> {
    override val route: String = "main/"

    @Composable
    override fun viewModel(): MainViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: MainViewModel) = MainScreen(presenter = viewModel)
}
