package com.sufyan.foodrecipie.di

import com.sufyan.foodrecipie.network.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun provideService(): RecipeService {
        return RecipeService.create()
    }
}
