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

        //var fragment = supportFragmentManager.findFragmentById(R.id.post) as PostFragment
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.post,PostFragment.newInstance(Post("dougy","this stuff is good","TITLE TEXT","my house")))
        transaction.addToBackStack(null)
        transaction.commit()


    }

    //private fun modifyLayout(config: Configuration) {
      //  if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
        //    setContentView(R.layout.activity_main)
        //} else {
          //  setContentView(R.layout.activity_main_land)
        //}
    //}


}