package com.example.citizen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.res.Configuration

class PostActivity : AppCompatActivity() {

    lateinit var post: PostFragment
    lateinit var comments: CommentListFragment

    companion object {
        var COMMENTLIST: MutableList<Comment> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        post = PostFragment.newInstance(Post("dougy","this stuff is good","TITLE TEXT","my house"))
        comments = CommentListFragment.newInstance()

        //var fragment = supportFragmentManager.findFragmentById(R.id.post) as PostFragment
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.post,post)
        //transaction.addToBackStack(null)
        transaction.replace(R.id.fragmentContainerView,CommentListFragment.newInstance())
        transaction.commit()


    }

    fun addComment(comment: Comment){
        COMMENTLIST.add(comment)
        comments.addComment(comment)
    }

}