package com.bankapp.navigation.domain

import androidx.annotation.VisibleForTesting
import com.bankapp.components.navigation.NavigationState
import com.bankapp.components.navigation.RouteNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class RouteNavigatorImpl @Inject constructor() : RouteNavigator {

    override val navigationState: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle)

    override fun onNavigated(state: NavigationState) {
        navigationState.compareAndSet(state, NavigationState.Idle)
    }

    override fun popToRoute(route: String) = navigate(NavigationState.PopToRoute(route))

    override fun navigateUp() = navigate(NavigationState.NavigateUp)

    override fun navigateToRoute(route: String) = navigate(NavigationState.NavigateToRoute(route))

    override fun navigateToGraph(graph: String) = navigate(NavigationState.NavigateToGraph(graph))

    @VisibleForTesting
    fun navigate(state: NavigationState) {
        navigationState.value = state
    }
}
