package com.example.citizen

import com.example.citizen.models.Post
import com.example.citizen.models.User

class Database {
    fun login(user: User): User {
        user.token = "test token"
        return user
    }

    fun getUser(id: Int) {

    }

    fun getPost(): Array<Post> {
        var p1 = Post(0, "First post.", "This is a body of the test post, it should contain the relevant information about the post.", 0, 0.0F, 0.0F)
        var p2 = Post(0, "Second post.", "This is a body of the second test post, it should contain the relevant information about the post.", 0, 0.0F, 0.0F)
        return arrayOf(p1, p2)
    }

    fun getPost(id: Int): Post {
        return Post(id, "This is a post called by id.", "This is the body of a post that is called by its unique databse id.", 0, 0F, 0F)
    }
}