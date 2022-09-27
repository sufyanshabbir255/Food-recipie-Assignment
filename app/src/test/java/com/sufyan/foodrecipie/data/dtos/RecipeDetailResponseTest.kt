package com.sufyan.foodrecipie.data.dtos

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sufyan.foodrecipie.ReadAssetFile
import org.junit.Assert
import org.junit.Test

class RecipeDetailResponseTest {

    @Test
    fun `test json response maps on recipe details response model`() {
        val response = readJsonFile()
        Assert.assertEquals(14, response.count)
        Assert.assertEquals("BBQ Season", response.results?.first()?.topics?.first()?.name)
        Assert.assertEquals("Lunch", response.results?.last()?.topics?.last()?.name)
    }

    private fun readJsonFile(): RecipeDetailResponse {
        val gson = GsonBuilder().create()
        val itemType = object : TypeToken<RecipeDetailResponse>() {}.type
        return gson.fromJson(ReadAssetFile.readFileFromTestResources("FoodRecipeDetailResponse.json"), itemType)
    }

}