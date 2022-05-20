package com.example.mycryptoc.model

import android.util.Log
import com.example.mycryptoc.data.CryptoData
import com.example.mycryptoc.listeners.ApiResponseListener
import com.example.mycryptoc.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserModel {

     fun getUserResponse(listener: ApiResponseListener) {
        ApiClient.getApi().getData().enqueue(object : Callback<CryptoData> {
            override fun onResponse(call: Call<CryptoData>, response: Response<CryptoData>) {
                if (response.body() != null && response.isSuccessful) {
                    Log.i(TAG, "onResponse: response $response")
                    Log.i(TAG, "onResponse: raw response ${response.raw()}")
                    Log.i(TAG, "onResponse: actual Data ${response.body()}")
                    listener.onSuccessResponse(response)

                }

            }

            override fun onFailure(call: Call<CryptoData>, t: Throwable) {
                listener.onFailuerResponse(t.message ?: "")
                t.printStackTrace()
            }

        })
    }

    companion object {
        private const val TAG = "UserModel"
    }
}