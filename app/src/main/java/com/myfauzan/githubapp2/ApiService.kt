package com.myfauzan.githubapp2

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Authorization: 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")
    @GET("search/users")
    fun findUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @Headers("Authorization: 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")
    @GET("users/{username}")
    fun getUser(
        @Path("username") username: String
    ): Call<DetailResponse>

    @Headers("Authorization: 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")
    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ) :Call<List<User>>

    @Headers("Authorization: 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")
    @GET("users/{username}/following")
    fun getFollowwing(
        @Path("username") username: String
    ) :Call<List<User>>
}