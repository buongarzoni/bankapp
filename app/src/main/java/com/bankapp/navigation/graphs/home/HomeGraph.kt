package com.bankapp.navigation.graphs.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation

const val HOME_GRAPH_ROUTE = "home/"

fun NavGraphBuilder.homeGraph(
    navHostController: NavHostController,
) {
    navigation(
        startDestination = HomeRoute.route,
        route = HOME_GRAPH_ROUTE,
    ) {
        HomeRoute.composable(this, navHostController)
    }
}
