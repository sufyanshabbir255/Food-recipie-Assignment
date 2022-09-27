package com.sufyan.foodrecipie.di

import com.sufyan.foodrecipie.data.network.IServiceProvider
import com.sufyan.foodrecipie.data.network.RecipeRepository
import com.sufyan.foodrecipie.data.network.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Singleton
    @Provides
    fun provideRecipeRepository(service: RecipeService): IServiceProvider {
        return RecipeRepository(service)
    }
}
