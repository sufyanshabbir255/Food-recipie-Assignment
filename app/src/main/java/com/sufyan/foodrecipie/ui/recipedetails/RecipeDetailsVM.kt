package com.sufyan.foodrecipie.ui.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sufyan.foodrecipie.IServiceProvider
import com.sufyan.foodrecipie.model.RecipeDetailResponse
import com.sufyan.foodrecipie.network.base.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeDetailsVM(var recipeService: IServiceProvider) : ViewModel(), IRecipeDetails {
    private var _recipeDetails: MutableLiveData<RecipeDetailResponse> = MutableLiveData()
    override val recipeDetails: LiveData<RecipeDetailResponse> = _recipeDetails

    override fun fetchRecipeDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = recipeService.getRecipeDetails()) {
                is NetworkResult.Success -> {
                    _recipeDetails.value = response.data
                }
                is NetworkResult.Error -> {

                }
            }
        }
    }
}