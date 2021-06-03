package com.example.citizen.models

class Post(var id: Int, var title: String, var body: String, var user: User, var comments: MutableList<Comment>?, var long: Double, var lat: Double, var upvotes: Int, var downvotes: Int) { }