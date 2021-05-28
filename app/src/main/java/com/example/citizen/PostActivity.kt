package com.example.citizen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.res.Configuration

class PostActivity : AppCompatActivity() {

    companion object {
        var COMMENTLIST: MutableList<Comment> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        var fragment = supportFragmentManager.findFragmentById(R.id.post) as PostFragment


    }

    //private fun modifyLayout(config: Configuration) {
      //  if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
        //    setContentView(R.layout.activity_main)
        //} else {
          //  setContentView(R.layout.activity_main_land)
        //}
    //}


}