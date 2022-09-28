package com.sufyan.foodrecipie.data.network

import com.sufyan.foodrecipie.BuildConfig
import com.sufyan.foodrecipie.data.base.CookiesInterceptor
import com.sufyan.foodrecipie.data.dtos.RecipeDetailResponse
import com.sufyan.foodrecipie.data.dtos.RecipeListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val timeoutRead = 60L
private const val timeoutConnect = 60L

interface RecipeService {
    @GET("recipes/list")
    suspend fun getRecipeListRequest(
        @Query("from") from: Int = 0,
        @Query("size") size: Int = 0
    ): Response<RecipeListResponse>

    @GET("recipes/list-similarities")
    suspend fun getRecipeDetailsRequest(@Query("recipe_id") recipeId: Int): Response<RecipeDetailResponse>

    companion object {
        private const val BASE_URL = "https://tasty.p.rapidapi.com"

        fun create(): RecipeService {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(CookiesInterceptor())
                .connectTimeout(timeoutConnect, TimeUnit.SECONDS)
                .readTimeout(timeoutRead, TimeUnit.SECONDS)
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