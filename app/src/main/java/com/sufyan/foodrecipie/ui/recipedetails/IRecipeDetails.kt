package com.sufyan.foodrecipie.ui.recipedetails

import androidx.lifecycle.LiveData
import com.sufyan.foodrecipie.data.dtos.RecipeDetailResponse

interface IRecipeDetails {
    val recipeDetails: LiveData<RecipeDetailResponse>
    fun getRecipeDetails()
}
