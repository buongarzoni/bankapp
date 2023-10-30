package com.bankapp.di.onboarding

import com.bankapp.core.auth.Auth as AuthCore
import com.bankapp.onboarding.actions.RegisterUser
import com.bankapp.onboarding.domain.Users
import com.bankapp.onboarding.domain.Auth as AuthOnboarding
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
    fun provideRegisterUser(
        authOnboarding: AuthOnboarding,
        authCore: AuthCore,
        users: Users,
    ) = RegisterUser(
        auth = authOnboarding,
        authCore = authCore,
        users = users,
    )
}
