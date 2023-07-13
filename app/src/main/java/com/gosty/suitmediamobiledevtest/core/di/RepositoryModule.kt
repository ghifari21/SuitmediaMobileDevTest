package com.gosty.suitmediamobiledevtest.core.di

import com.gosty.suitmediamobiledevtest.core.data.UserRepositoryImpl
import com.gosty.suitmediamobiledevtest.core.domain.repositories.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideUserRepository(repository: UserRepositoryImpl): UserRepository
}