package com.example.citizen.models

class Comment(var id: Int, var postId: Int, var userId: Int, var replyTo:Int, var body: String, var upvotes: Int, var downvotes: Int) { }