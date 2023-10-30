package com.bankapp.navigation.domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.bankapp.components.navigation.NavigationState
import com.bankapp.components.navigation.RouteNavigator

interface NavRoute<T : RouteNavigator> {

    val route: String

    @Composable
    fun Content(viewModel: T)

    @Composable
    fun viewModel(): T

    fun getArguments(): List<NamedNavArgument> = listOf()

    fun composable(
        builder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        builder.composable(route, getArguments()) {
            val viewModel = viewModel()
            val viewStateAsState by viewModel.navigationState.collectAsState()

            LaunchedEffect(viewStateAsState) {
                updateNavigationState(navHostController, viewStateAsState, viewModel::onNavigated)
            }

            Content(viewModel)
        }
    }

    private fun updateNavigationState(
        navHostController: NavHostController,
        navigationState: NavigationState,
        onNavigated: (navState: NavigationState) -> Unit,
    ) {
        when (navigationState) {
            is NavigationState.NavigateToRoute -> {
                if (navigationState.route == navHostController.currentDestination?.route) return
                navHostController.navigate(navigationState.route) {
                    launchSingleTop = true
                }
                onNavigated(navigationState)
            }
            is NavigationState.PopToRoute -> {
                navHostController.popBackStack(navigationState.staticRoute, false)
                onNavigated(navigationState)
            }
            is NavigationState.NavigateUp -> {
                navHostController.navigateUp()
            }
            is NavigationState.Idle -> {
            }
            is NavigationState.NavigateToGraph -> {
                navHostController.popBackStack()
                navHostController.navigate(navigationState.graph) {
                    popUpTo(navHostController.graph.id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
                onNavigated(navigationState)
            }
        }
    }
}

fun <T> SavedStateHandle.getOrThrow(key: String): T =
    get<T>(key) ?: throw IllegalArgumentException(
        "Mandatory argument $key missing in arguments."
    )
