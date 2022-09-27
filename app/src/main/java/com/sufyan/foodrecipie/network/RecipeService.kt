package com.sufyan.foodrecipie.network

import com.sufyan.foodrecipie.model.RecipeDetailResponse
import com.sufyan.foodrecipie.model.RecipeListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RecipeService {
    @GET("recipes/list")
    suspend fun getRecipeListRequest(): Response<RecipeListResponse>

    @GET("recipes/list-similarities")
    suspend fun getRecipeDetailsRequest(): Response<RecipeDetailResponse>

    companion object {
        private const val BASE_URL = "https://rapidapi.com/apidojo/api/tasty/"

        fun create(): RecipeService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RecipeService::class.java)
        }
    }
}