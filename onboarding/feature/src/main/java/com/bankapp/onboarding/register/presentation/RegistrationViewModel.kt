package com.bankapp.onboarding.register.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bankapp.components.navigation.RouteNavigator
import com.bankapp.onboarding.register.domain.RegistrationView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
) : ViewModel(),
    RegistrationPresenter,
    RouteNavigator by routeNavigator {

    private val _registrationView = mutableStateOf(RegistrationView.UserDataView)
    override val registrationView: State<RegistrationView> = _registrationView

}
