package com.krunal.androxploit.di

import android.content.Context
import com.krunal.androxploit.domain.usecase.network.hosts.LoadNmapUseCase
import com.krunal.androxploit.presentation.ui.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Responsible for providing singleton object dependency of
    // Navigation manager
    @Singleton
    @Provides
    fun providesNavigationManager(): NavigationManager = NavigationManager()

//    @Singleton
//    @Provides
//    suspend fun providesNmapPath(loadNmapUseCase: LoadNmapUseCase): String = loadNmapUseCase()

//    @Singleton
//    @Provides
//    fun providesNetworkDetails(networkDetailsUseCase: NetworkDetailsUseCase): NetworkDetails =
//        networkDetailsUseCase()
}