package com.bankapp.navigation.graphs.root

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bankapp.navigation.graphs.onboarding.ONBOARDING_GRAPH_ROUTE
import com.bankapp.navigation.graphs.onboarding.onboardingGraph

@Composable
fun RootGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = ONBOARDING_GRAPH_ROUTE,
        route = "root/",
    ) {
        onboardingGraph(navHostController)
    }
}
