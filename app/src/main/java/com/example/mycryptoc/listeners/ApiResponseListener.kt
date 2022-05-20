package com.example.mycryptoc.listeners

import retrofit2.Response

interface ApiResponseListener {

    fun onSuccessResponse(response: Response<*>)
    fun onFailuerResponse(message: String)
}