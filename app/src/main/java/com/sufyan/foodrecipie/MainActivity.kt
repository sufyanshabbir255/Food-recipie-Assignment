package com.sufyan.foodrecipie

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sufyan.foodrecipie.databinding.ActivityMainBinding
import com.sufyan.foodrecipie.ui.ViewState
import com.sufyan.foodrecipie.ui.adapter.RecipeListAdapter
import com.sufyan.foodrecipie.ui.recipelist.IRecipeList
import com.sufyan.foodrecipie.ui.recipelist.RecipeListVM
import com.sufyan.foodrecipie.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mViewBinding: ActivityMainBinding
    private val viewModel: IRecipeList by viewModels<RecipeListVM>()

    @Inject
    lateinit var recipeListAdapter: RecipeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        mViewBinding.rvRecipeList.adapter = recipeListAdapter
        addObserver()
    }

    private fun addObserver() {
        viewModel.viewState.observe(this, ::bindViewState)
    }

    private fun bindViewState(viewState: ViewState) {
        hideLoadingView()
        when (viewState) {
            is ViewState.Loading -> {
                showLoadingView()
            }
            is ViewState.ResponseLoaded -> {
                if (!(viewState.response.isNullOrEmpty())) {
                    recipeListAdapter.setList(viewState.response)
                    showDataView(true)
                } else {
                    showDataView(false)
                }
            }
            is ViewState.ResponseLoadFailure -> {
                toast(msg = viewState.errorMessage)
                showDataView(false)
            }
        }
    }

    private fun showLoadingView() {
        mViewBinding.lyLoadingView.shimmerFrameLayout.visibility = VISIBLE
        mViewBinding.rvRecipeList.visibility = GONE
    }

    private fun showDataView(show: Boolean) {
        mViewBinding.rvRecipeList.visibility = if (show) VISIBLE else GONE
    }

    private fun hideLoadingView() {
        mViewBinding.lyLoadingView.shimmerFrameLayout.visibility = GONE
    }

}
