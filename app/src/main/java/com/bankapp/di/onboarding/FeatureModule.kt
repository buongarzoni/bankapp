package com.bankapp.di.onboarding

import android.content.Context
import com.bankapp.components.navigation.RouteNavigator
import com.bankapp.navigation.graphs.onboarding.RegistrationRoute
import com.bankapp.onboarding.login.presentation.LoginViewModel
import com.bankapp.onboarding.register.presentation.RegistrationViewModel
import com.bankapp.utils.UriProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @ViewModelScoped
    @Provides
    fun registrationViewModel(
        routeNavigator: RouteNavigator,
        uriProvider: UriProvider,
    ) = RegistrationViewModel(
        routeNavigator = routeNavigator,
        getNewUri = { uriProvider.newUri() },
    )

    @ViewModelScoped
    @Provides
    fun uriProvider(
        @ApplicationContext context: Context
    ) = UriProvider(context)

}
