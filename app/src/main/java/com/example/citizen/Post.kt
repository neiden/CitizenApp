package com.example.citizen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
/**
 * Class that represents a comment.
 * TODO: Needs comment content, commentor, up/downvotes, sub comments, location?
 */

@Parcelize
class Post (var op: String, var content: String, var title: String, var location: String): Parcelable{ //TODO: Change location to different variable type?
    var score = 0
    var comments = mutableListOf<Comment>()

    fun downvote(){
        score--
    }

    fun upvote(){
        score++
    }

    fun addComment(cmt: Comment){
        comments.add(cmt)
    }
}