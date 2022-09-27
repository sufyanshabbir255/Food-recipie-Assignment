package com.sufyan.foodrecipie.network.base

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

const val KEY_AUTHORIZATION = "X-RapidAPI-Key"

class CookiesInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = addCookiesInRequest(chain.request())
        return chain.proceed(request)
    }

    private fun addCookiesInRequest(request: Request): Request {
        val builder = request.newBuilder()
        builder.addHeader(KEY_AUTHORIZATION, "e503bd4e44msh884846bc3d2b146p164cf1jsn216f0a44903a")
        return builder.build()
    }
}
