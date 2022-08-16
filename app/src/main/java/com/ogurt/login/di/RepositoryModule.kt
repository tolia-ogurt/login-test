package com.ogurt.login.di

import com.ogurt.login.repo.LoginRepository
import com.ogurt.login.repo.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindVideosListRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}
