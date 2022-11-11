package com.myfauzan.githubapp2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    val detailUser = MutableLiveData<DetailResponse>()

    fun setDetailUsers(username: String){
        ApiConfig.getApiService()
            .getUser(username)
            .enqueue(object : Callback<DetailResponse> {
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    if (response.isSuccessful){
                        detailUser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.d("Failure: ", t.message.toString())
                }

            })
    }

    fun getDetailUsers() : LiveData<DetailResponse>{
        return detailUser
    }
}