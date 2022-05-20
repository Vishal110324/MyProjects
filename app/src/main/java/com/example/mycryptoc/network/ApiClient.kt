package com.example.mycryptoc.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {

    private fun setRetrofit() {
        val httpClient = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        apiInterface = retrofit.create(ApiInterface::class.java)

    }

    companion object {
        private const val BASE_URL = "https://api.coincap.io/"
        private var apiClient: ApiClient? = null
        private lateinit var apiInterface: ApiInterface

        private fun createApiInstance(): ApiClient? {
            if (apiClient == null) apiClient = ApiClient()
            return apiClient
        }

        fun getApi(): ApiInterface {
            createApiInstance()?.setRetrofit()
            return apiInterface
        }
    }

}