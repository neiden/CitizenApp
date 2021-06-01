package com.example.citizen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

/**
 * Main activity currently opens a post fragment immediately - change for final product.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.post,PostFragment.newInstance(Post("dougy","this stuff is good","TITLE TEXT","my house")))
        transaction.addToBackStack(null)
        transaction.commit()
        //var intent = Intent(this, PostActivity::class.java)
        //startActivity(intent)
    }
}