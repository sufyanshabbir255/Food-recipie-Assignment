package com.sufyan.foodrecipie.ui.recipedetails

import androidx.lifecycle.LiveData
import com.sufyan.foodrecipie.data.dtos.RecipeDetailResponse
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.ui.SimilarRecipeViewState

interface IRecipeDetails {
    val recipeDetails: LiveData<RecipeDetailResponse>
    val recipe: LiveData<RecipeListResponse.Recipe>
    val viewState: LiveData<SimilarRecipeViewState>
    fun getRecipeDetails(recipeId: Int)
    fun setData(recipe: RecipeListResponse.Recipe)
}
