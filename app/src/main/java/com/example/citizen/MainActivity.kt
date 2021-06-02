package com.example.citizen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setUpTabs()




    }
    private fun setUpTabs(){
        val adapter = LoginAdapter(supportFragmentManager)
        adapter.addFragment(LoginTab(this),"Login")
        adapter.addFragment(SignUpTab(),"SignUp")
        val viewPager: ViewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)!!.text = "Login"
        tabLayout.getTabAt(1)!!.text = "Sign Up"


    }

}