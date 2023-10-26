package com.bankapp.di.viewmodel

import com.bankapp.components.navigation.RouteNavigator
import com.bankapp.navigation.domain.RouteNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @ViewModelScoped
    @Binds
    abstract fun bindRouteNavigator(routeNavigatorImpl: RouteNavigatorImpl): RouteNavigator
}
