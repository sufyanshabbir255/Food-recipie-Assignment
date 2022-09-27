package com.sufyan.foodrecipie.network

import com.sufyan.foodrecipie.model.RecipeDetailResponse
import com.sufyan.foodrecipie.model.RecipeListResponse
import com.sufyan.foodrecipie.network.base.NetworkResult

interface IServiceProvider {
    suspend fun fetchRecipeList(): NetworkResult<RecipeListResponse>
    suspend fun fetchRecipeDetails(): NetworkResult<RecipeDetailResponse>
}
