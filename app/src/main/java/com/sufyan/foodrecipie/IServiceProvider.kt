package com.sufyan.foodrecipie

import com.sufyan.foodrecipie.model.RecipeListResponse

interface IServiceProvider {
    suspend fun getRecipeList(): RecipeListResponse
}