package com.myfauzan.githubapp2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {
    val dataFollowers = MutableLiveData<List<User>>()

    fun setDataFollowers(username: String) {
        ApiConfig.getApiService()
            .getFollowers(username)
            .enqueue(object : Callback<List<User>>{
                override fun onResponse(
                    call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful){
                        dataFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.d("onFailure: ", t.message.toString())
                }

            })
    }

    fun getDataFollowers() : LiveData<List<User>>{
        return dataFollowers
    }
}