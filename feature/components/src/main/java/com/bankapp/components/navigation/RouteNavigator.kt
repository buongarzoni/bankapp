package com.bankapp.components.navigation

import kotlinx.coroutines.flow.StateFlow

interface RouteNavigator {
    fun onNavigated(state: NavigationState)
    fun navigateUp()
    fun popToRoute(route: String)
    fun navigateToRoute(route: String)
    fun navigateToGraph(graph: String)

    val navigationState: StateFlow<NavigationState>
}
