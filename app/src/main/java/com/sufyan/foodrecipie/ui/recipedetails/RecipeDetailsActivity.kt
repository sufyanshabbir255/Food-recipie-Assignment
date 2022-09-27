package com.sufyan.foodrecipie.ui.recipedetails

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sufyan.foodrecipie.data.dtos.RecipeDetailResponse
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import com.sufyan.foodrecipie.databinding.ActivityRecipeDetailsBinding
import com.sufyan.foodrecipie.ui.SimilarRecipeViewState
import com.sufyan.foodrecipie.ui.recipedetails.adapter.SimilarRecipeAdapter
import com.sufyan.foodrecipie.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeDetailsActivity : AppCompatActivity() {
    private lateinit var mViewBinding: ActivityRecipeDetailsBinding
    private val viewModel: IRecipeDetails by viewModels<RecipeDetailsVM>()

    @Inject
    lateinit var similarRecipeAdapter: SimilarRecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        initRecyclerView()
        addObserver()
        getExtras()
    }

    private fun initRecyclerView() {
        similarRecipeAdapter.onItemClickListener = itemClickListener
        mViewBinding.rvSimilarRecipes.adapter = similarRecipeAdapter
    }

    private fun getExtras() =
        intent.extras?.getParcelable<RecipeListResponse.Recipe>("data")?.let { viewModel.setData(it) }


    private fun addObserver() {
        viewModel.viewState.observe(this, ::bindViewState)
        viewModel.recipe.observe(this, ::setUIData)
    }

    private fun setUIData(recipe: RecipeListResponse.Recipe) {
        with(mViewBinding) {
            Glide.with(ivThumbnail.context)
                .load(recipe.thumbnailUrl)
                .into(ivThumbnail)
            tvRecipeName.text = recipe.name
            tvRecipeDesc.text = recipe.description
        }
        viewModel.getRecipeDetails()
    }

    private val itemClickListener = { view: View, position: Int, data: RecipeDetailResponse.Recipe? ->

    }

    private fun bindViewState(viewState: SimilarRecipeViewState) {
        hideLoadingView()
        when (viewState) {
            is SimilarRecipeViewState.Loading -> {
                showLoadingView()
            }
            is SimilarRecipeViewState.ResponseLoaded -> {
                if (!(viewState.response.isNullOrEmpty())) {
                    similarRecipeAdapter.setList(viewState.response)
                    showDataView(true)
                } else {
                    showDataView(false)
                }
            }
            is SimilarRecipeViewState.ResponseLoadFailure -> {
                toast(msg = viewState.errorMessage)
                showDataView(false)
            }
        }
    }

    private fun showLoadingView() {
        mViewBinding.lyLoadingView.shimmerFrameLayout.visibility = VISIBLE
        mViewBinding.rvSimilarRecipes.visibility = GONE
    }

    private fun showDataView(show: Boolean) {
        mViewBinding.rvSimilarRecipes.visibility = if (show) VISIBLE else GONE
    }

    private fun hideLoadingView() {
        mViewBinding.lyLoadingView.shimmerFrameLayout.visibility = GONE
    }
}
