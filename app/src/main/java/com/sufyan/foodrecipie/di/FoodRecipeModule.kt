package com.sufyan.foodrecipie.di

import com.sufyan.foodrecipie.IServiceProvider
import com.sufyan.foodrecipie.RecipeRepository
import com.sufyan.foodrecipie.RecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class FoodRecipeModule {
    @Singleton
    @Provides
    fun provideRecipeRepository(service: RecipeService): IServiceProvider {
        return RecipeRepository(service)
    }
}
