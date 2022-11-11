package com.myfauzan.githubapp2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel: ViewModel() {
    val listUsers = MutableLiveData<List<User>>()

    fun setSearchUser(query: String){
        ApiConfig.getApiService()
            .findUser(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d( "Failure: ", t.message.toString())
                }

            })
    }

    fun getSearchUsers() : LiveData<List<User>>{
        return listUsers
    }
}