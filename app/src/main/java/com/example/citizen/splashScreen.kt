package com.example.citizen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val redditPolice = findViewById<ImageView>(R.id.redditPolice)
        val names = findViewById<TextView>(R.id.names)
        val title = findViewById<TextView>(R.id.titleText)

        names.alpha = 0f
        redditPolice.alpha = 0f
        title.alpha = 0f

        val root = redditPolice.rootView


        title.animate().setDuration(3000).alpha(2f)
        names.animate().setDuration(3000).alpha(2f)
        redditPolice.animate().setDuration(3000).alpha(2f).withEndAction {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }


    }
}