package com.bankapp.di.home

import com.bankapp.core.auth.Auth
import com.bankapp.home.model.actions.FetchBankDetails
import com.bankapp.home.model.domain.Accounts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ActionsModule {
    @ViewModelScoped
    @Provides
    fun provideFetchBankDetails(
        auth: Auth,
        accounts: Accounts,
    ) = FetchBankDetails(
        auth = auth,
        accounts = accounts
    )
}
