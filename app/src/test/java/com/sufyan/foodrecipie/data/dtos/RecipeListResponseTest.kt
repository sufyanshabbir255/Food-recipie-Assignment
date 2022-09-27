package com.sufyan.foodrecipie.data.dtos

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sufyan.foodrecipie.ReadAssetFile
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import org.junit.Assert
import org.junit.Test


class RecipeListResponseTest {

    @Test
    fun `test json response maps to recipe list model`() {
        val recipeListResponse = readJsonFile()
        Assert.assertEquals(20, recipeListResponse.recipes?.size)
        Assert.assertEquals("How To Make Classic French Toast", recipeListResponse.recipes?.first()?.name)
        Assert.assertEquals("Avocado Citrus Salad", recipeListResponse.recipes?.last()?.name)
    }

    private fun readJsonFile(): RecipeListResponse {
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<RecipeListResponse>() {}.type
        return gson.fromJson(ReadAssetFile.readFileFromTestResources("FoodRecipeResponse.json"), itemType)
    }
}