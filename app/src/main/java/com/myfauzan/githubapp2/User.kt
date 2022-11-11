package com.myfauzan.githubapp2

data class User(
    var name: String,
    var login: String,
    var id: Int,
    var avatar_url: String,
    var follower_url: String,
    var following_url: String,
    var location: String,
    var company: String,
    var followers: Int,
    var following: Int
)
