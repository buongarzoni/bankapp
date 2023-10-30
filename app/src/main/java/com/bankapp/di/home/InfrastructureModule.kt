package com.bankapp.di.home

import com.bankapp.home.model.domain.Accounts
import com.bankapp.infrastructure.database.firebase.home.FirebaseAccounts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InfrastructureModule {
    @Provides
    @Singleton
    fun provideAccounts(): Accounts = FirebaseAccounts()
}
