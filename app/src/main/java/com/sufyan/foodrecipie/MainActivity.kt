package com.sufyan.foodrecipie

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sufyan.foodrecipie.databinding.ActivityMainBinding
import com.sufyan.foodrecipie.ui.recipelist.IRecipeList
import com.sufyan.foodrecipie.ui.recipelist.RecipeListVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: IRecipeList by viewModels<RecipeListVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getRecipeList()
    }

}
