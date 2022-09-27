package com.sufyan.foodrecipie.ui.recipelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sufyan.foodrecipie.data.base.NetworkResult
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.data.network.IServiceProvider
import com.sufyan.foodrecipie.ui.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListVM @Inject constructor(var recipeServiceProvider: IServiceProvider) : ViewModel(), IRecipeList {
    private var _recipeList: MutableLiveData<RecipeListResponse> = MutableLiveData()
    override val recipeList: LiveData<RecipeListResponse> = _recipeList

    private val _viewState = MutableLiveData<ViewState>()
    override val viewState: LiveData<ViewState> = _viewState

    init {
        getRecipeList()
    }

    override fun getRecipeList() {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.postValue(ViewState.Loading)
            when (val response = recipeServiceProvider.fetchRecipeList()) {
                is NetworkResult.Success -> {
                    _viewState.postValue(ViewState.ResponseLoaded(response.data.recipes))
                    _recipeList.postValue(response.data)
                }
                is NetworkResult.Error -> {
                    _viewState.postValue(ViewState.ResponseLoadFailure(response.error.message ?: ""))
                    _recipeList.postValue(RecipeListResponse())
                }
            }
        }
    }
}