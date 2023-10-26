package com.bankapp.di.onboarding

import com.bankapp.components.navigation.RouteNavigator
import com.bankapp.navigation.graphs.onboarding.RegistrationRoute
import com.bankapp.onboarding.login.presentation.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class FeatureModule {

    @ViewModelScoped
    @Provides
    fun loginViewModel(
        routeNavigator: RouteNavigator,
    ) = LoginViewModel(
        routeNavigator = routeNavigator,
        navigateToRegistration = { routeNavigator.navigateToRoute(RegistrationRoute.route) },
    )

}
