package com.example.citizen

/**
 * Class that represents a comment.
 * TODO: Needs comment content, commentor, up/downvotes, sub comments, location?
 */
class Comment (var op: String, var content: String){ //TODO: Change location to different variable type?
    var subComments = mutableListOf<Comment>()

    fun addComment(cmt: Comment){
        subComments.add(cmt)
    }
}