package com.example.citizen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), PostDialogListener {
    lateinit var newPostButton: Button

    companion object{
        var postNumber = 0
        var POSTLIST: MutableList<Post> = mutableListOf()
    }

    override fun onPassData(data: String) {
        var postList: RecyclerView = findViewById(R.id.postList)
        var dataArray = data.split(",").toTypedArray()
        var op = dataArray[0]
        var title = dataArray[2]
        var description = dataArray[1]
        var location = dataArray[3]
        POSTLIST.add(Post("$op", "$description", "$title", "$location"))
        postList.adapter?.notifyDataSetChanged()
        postNumber++
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var postList: RecyclerView = findViewById(R.id.postList)
        postList.adapter = MyPostRecyclerViewAdapter(POSTLIST, this)
        postList.layoutManager = LinearLayoutManager(this)

        POSTLIST.add(Post("John42069", "This is fucked", "Help me", "Seattle, WA"))
        postList.adapter?.notifyDataSetChanged()
        postNumber++

        newPostButton = findViewById(R.id.addPost)
        newPostButton.setOnClickListener{
            var postDialogFrag = CreatePostDialogFragment()
            postDialogFrag.show(supportFragmentManager, "fragment_create_post_dialog")
        }
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.post,PostFragment.newInstance(Post("dougy","this stuff is good","TITLE TEXT","my house")))
        transaction.addToBackStack(null)
        transaction.commit()
        //var intent = Intent(this, PostActivity::class.java)
        //startActivity(intent)
    }


}