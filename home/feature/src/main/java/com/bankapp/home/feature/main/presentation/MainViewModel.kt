package com.bankapp.home.feature.main.presentation

import androidx.lifecycle.ViewModel
import com.bankapp.components.navigation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
) : ViewModel(),
    MainPresenter,
    RouteNavigator by routeNavigator {
}
