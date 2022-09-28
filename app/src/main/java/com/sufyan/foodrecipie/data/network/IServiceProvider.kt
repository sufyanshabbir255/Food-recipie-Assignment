package com.sufyan.foodrecipie.data.network

import com.sufyan.foodrecipie.data.base.NetworkResult
import com.sufyan.foodrecipie.data.dtos.RecipeDetailResponse
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse

interface IServiceProvider {
    suspend fun fetchRecipeList(from: Int = 0, size: Int = 0): NetworkResult<RecipeListResponse>
    suspend fun fetchRecipeDetails(recipeId: Int): NetworkResult<RecipeDetailResponse>
}
