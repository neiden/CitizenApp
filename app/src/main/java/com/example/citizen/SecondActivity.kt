package com.example.citizen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val textView : TextView = findViewById(R.id.secondView)
        val textView2: TextView = findViewById(R.id.textView12)
        val textView3: TextView = findViewById(R.id.textView122)
        val textView4: TextView = findViewById(R.id.textView13)


        val email = intent.getStringExtra("email")
        val pass = intent.getStringExtra("pass")
        val name = intent.getStringExtra("name")
        val badge = intent.getStringExtra("badge")

        textView.text = email
        textView2.text = pass
        textView3.text = name
        textView4.text = badge


    }
}