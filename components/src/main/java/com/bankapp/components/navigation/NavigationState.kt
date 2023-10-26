package com.bankapp.components.navigation

sealed class NavigationState {

    object Idle : NavigationState()

    object NavigateUp : NavigationState()

    data class NavigateToRoute(val route: String) : NavigationState()

    data class NavigateToGraph(val graph: String) : NavigationState()

    data class PopToRoute(val staticRoute: String) : NavigationState()

}
