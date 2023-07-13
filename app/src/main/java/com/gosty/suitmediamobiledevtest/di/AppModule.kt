package com.gosty.suitmediamobiledevtest.di

import com.gosty.suitmediamobiledevtest.core.domain.usecases.UserInteractor
import com.gosty.suitmediamobiledevtest.core.domain.usecases.UserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase
}