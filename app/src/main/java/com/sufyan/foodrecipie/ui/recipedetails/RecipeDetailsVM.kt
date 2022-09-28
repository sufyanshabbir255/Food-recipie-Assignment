package com.sufyan.foodrecipie.ui.recipedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sufyan.foodrecipie.data.base.NetworkResult
import com.sufyan.foodrecipie.data.dtos.RecipeDetailResponse
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.data.network.IServiceProvider
import com.sufyan.foodrecipie.ui.SimilarRecipeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsVM @Inject constructor(var recipeService: IServiceProvider) : ViewModel(), IRecipeDetails {
    private var _recipeDetails: MutableLiveData<RecipeDetailResponse> = MutableLiveData()
    override val recipeDetails: LiveData<RecipeDetailResponse> = _recipeDetails
    private var _recipe: MutableLiveData<RecipeListResponse.Recipe> = MutableLiveData()
    override val recipe: LiveData<RecipeListResponse.Recipe> = _recipe
    private val _viewState = MutableLiveData<SimilarRecipeViewState>()
    override val viewState: LiveData<SimilarRecipeViewState> = _viewState

    override fun setData(recipe: RecipeListResponse.Recipe) {
        _recipe.value = recipe
    }

    override fun getRecipeDetails(recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.postValue(SimilarRecipeViewState.Loading)
            when (val response = recipeService.fetchRecipeDetails(recipeId)) {
                is NetworkResult.Success -> {
                    _viewState.postValue(SimilarRecipeViewState.ResponseLoaded(response.data.results))
                    _recipeDetails.postValue(response.data)
                }
                is NetworkResult.Error -> {
                    _viewState.postValue(SimilarRecipeViewState.ResponseLoadFailure(response.error.message ?: ""))
                    _recipeDetails.postValue(RecipeDetailResponse())
                }
            }
        }
    }
}