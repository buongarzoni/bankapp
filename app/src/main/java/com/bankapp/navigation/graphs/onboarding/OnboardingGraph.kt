package com.bankapp.navigation.graphs.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

const val ONBOARDING_GRAPH_ROUTE = "onboarding/"

fun NavGraphBuilder.onboardingGraph(
    navHostController: NavHostController,
) {
    navigation(
        startDestination = LoginRoute.route,
        route = ONBOARDING_GRAPH_ROUTE,
    ) {
        LoginRoute.composable(this, navHostController)
    }
}
