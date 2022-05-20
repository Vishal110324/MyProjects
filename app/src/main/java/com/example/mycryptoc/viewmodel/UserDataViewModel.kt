package com.example.mycryptoc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycryptoc.data.CryptoData
import com.example.mycryptoc.listeners.ApiResponseListener
import com.example.mycryptoc.model.UserModel
import retrofit2.Response

class UserDataViewModel : ViewModel() {

    private var userModel: UserModel = UserModel()

    var usersLiveData: LiveData<CryptoData> = getUserData()

    private fun getUserData(): LiveData<CryptoData> {
        val mList = MutableLiveData<CryptoData>()
        userModel.getUserResponse(object : ApiResponseListener {
            override fun onSuccessResponse(response: Response<*>) {
                if (response.body() != null) {
                    val data = response.body() as CryptoData
                    mList.postValue(data)
                }

            }

            override fun onFailuerResponse(message: String) {
                Log.e(TAG, "onFailureResponse: $message")
            }

        })
        return mList
    }

    companion object {
        private const val TAG = "UserDataViewModel"
    }
}