package com.example.citizen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citizen.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class FeedActivity : AppCompatActivity(), PostDialogListener {
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
        setContentView(R.layout.activity_feed)

        var db: Database = Database(this)

        db.getPost()




        var postList: RecyclerView = findViewById(R.id.postList)
        val bar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
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

        bar.setOnNavigationItemSelectedListener {
            when(it.itemId){    //TODO Put API calls to get specific feeds
                R.id.home->Log.d("NAVBAR","HOME")
                R.id.world->Log.d("NAVBAR","ZA WORLDO")
                R.id.personal->Log.d("NAVBAR","ME MYSELF AND I")
            }
            true
        }


    }


}