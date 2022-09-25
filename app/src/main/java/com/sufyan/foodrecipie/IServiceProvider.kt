package com.sufyan.foodrecipie

import com.sufyan.foodrecipie.model.RecipeDetailResponse
import com.sufyan.foodrecipie.model.RecipeListResponse
import com.sufyan.foodrecipie.network.base.NetworkResult

interface IServiceProvider {
    suspend fun getRecipeList(): NetworkResult<RecipeListResponse>
    suspend fun getRecipeDetails(): NetworkResult<RecipeDetailResponse>
}
