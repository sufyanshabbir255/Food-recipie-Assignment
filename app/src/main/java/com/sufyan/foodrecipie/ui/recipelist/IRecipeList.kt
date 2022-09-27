package com.sufyan.foodrecipie.ui.recipelist

import androidx.lifecycle.LiveData
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse

interface IRecipeList {
    val recipeList: LiveData<RecipeListResponse>
    fun getRecipeList()
}
