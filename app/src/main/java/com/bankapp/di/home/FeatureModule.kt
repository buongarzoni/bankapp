package com.bankapp.di.home

import com.bankapp.components.navigation.RouteNavigator
import com.bankapp.home.feature.main.presentation.MainViewModel
import com.bankapp.home.model.actions.FetchBankDetails
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
    fun provideMainViewModel(
        fetchBankDetails: FetchBankDetails,
        routeNavigator: RouteNavigator,
    ) = MainViewModel(
        fetchBankDetails = fetchBankDetails,
        routeNavigator = routeNavigator,
    )
}
