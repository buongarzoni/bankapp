package com.bankapp.di.onboarding

import com.bankapp.core.auth.Auth as AuthCore
import com.bankapp.infrastructure.auth.firebase.FirebaseAuth
import com.bankapp.infrastructure.database.firebase.FirebaseRemoteDatabase
import com.bankapp.onboarding.domain.Auth as AuthOnboarding
import com.bankapp.onboarding.domain.Users
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
    fun provideUsers(): Users = FirebaseRemoteDatabase()

    @Provides
    @Singleton
    fun provideAuthCore(firebaseAuth: FirebaseAuth): AuthCore = firebaseAuth

    @Provides
    @Singleton
    fun provideAuthOnboarding(firebaseAuth: FirebaseAuth): AuthOnboarding = firebaseAuth


    @Provides
    @Singleton
    fun provideFirebaseRemoteDatabase() = FirebaseRemoteDatabase()

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth()
}
