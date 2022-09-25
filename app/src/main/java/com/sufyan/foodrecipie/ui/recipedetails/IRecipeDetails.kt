package com.sufyan.foodrecipie.ui.recipedetails

import androidx.lifecycle.LiveData
import com.sufyan.foodrecipie.model.RecipeDetailResponse

interface IRecipeDetails {
    val recipeDetails: LiveData<RecipeDetailResponse>
    fun fetchRecipeDetails()
}
