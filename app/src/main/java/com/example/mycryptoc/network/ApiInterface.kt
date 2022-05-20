package com.example.mycryptoc.network

import com.example.mycryptoc.data.CryptoData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("v2/assets")
    fun getData():Call<CryptoData>
}