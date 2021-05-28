package com.example.citizen

/**
 * Class that represents a comment.
 * TODO: Needs comment content, commentor, up/downvotes, sub comments, location?
 */
class Post (var op: String, var content: String, var location: String){ //TODO: Change location to different variable type?
    var up = 0
    var down = 0
    var comments = mutableListOf<Comment>()

    fun downvote(){
        down++
    }

    fun upvote(){
        up++
    }

    fun addComment(cmt: Comment){
        comments.add(cmt)
    }
}