package com.example.citizen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.res.Configuration
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PostActivity : AppCompatActivity(), CommentDialogListener {

    lateinit var post: PostFragment
    lateinit var comments: CommentListFragment
    lateinit var title: TextView
    lateinit var content: TextView
    lateinit var commentButton: Button

    companion object {
        var COMMENTLIST: MutableList<Comment> = mutableListOf()
    }

    override fun onPassData(data: String) {
        var commentList: RecyclerView = findViewById(R.id.commentList)
        var dataArray = data.split(",").toTypedArray()
        var op = dataArray[0]
        var content = dataArray[1]
        COMMENTLIST.add(Comment("$op", "$content"))
        commentList.adapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        var commentList: RecyclerView = findViewById(R.id.commentList)
        var incomingPost: Post ?= intent.getParcelableExtra<Post>("POST")


        commentList.adapter = MyCommentListRecyclerViewAdapter(COMMENTLIST)
        commentList.layoutManager = LinearLayoutManager(this)

        title = findViewById(R.id.postTitle)
        title.text = incomingPost?.title
        content = findViewById(R.id.postContent)
        content.text = incomingPost?.content
        commentButton = findViewById(R.id.commentButton)
        
        comments = CommentListFragment.newInstance()
        COMMENTLIST.add(Comment("John", "I am the greatest of all time"))
        COMMENTLIST.add(Comment("xXslay3r69Xx", "Doug's an alright guy"))
        COMMENTLIST.add(Comment("Don", "That's definitely good stuff"))

        Log.d("EEE", "${COMMENTLIST.toString()}")
        commentList.adapter?.notifyDataSetChanged()


        commentButton.setOnClickListener{
            var commentDialogFrag = CreateCommentDialogFragment()
            commentDialogFrag.show(supportFragmentManager, "fragment_create_comment_dialog")
        }

//        //var fragment = supportFragmentManager.findFragmentById(R.id.post) as PostFragment
//        val fragmentManager = supportFragmentManager
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.post,post)
//        //transaction.addToBackStack(null)
//        transaction.replace(R.id.fragmentContainerView,CommentListFragment.newInstance())
//        transaction.commit()


    }

    fun addComment(comment: Comment){
        COMMENTLIST.add(comment)
        comments.addComment(comment)
    }

}