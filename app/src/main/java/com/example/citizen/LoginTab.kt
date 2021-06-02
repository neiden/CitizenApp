package com.example.citizen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginTab : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.login_fragment,container,false)


        val username :EditText = view.findViewById(R.id.logUsername)
        val pass: EditText = view.findViewById(R.id.logPass)


        val btnLogin :Button = view.findViewById(R.id.buttonLogin)
        btnLogin.setOnClickListener{

            val usernameString = username.text.toString()
            val passString = pass.text.toString()

            Log.d("DEBUG", usernameString)
            Log.d("DEBUG",passString)
            Log.d("DEBUG","BUTTON PRESS")

            if(usernameString.isEmpty() || passString.isEmpty()){
                Toast.makeText(activity,"Enter Valid Login Credentials",Toast.LENGTH_LONG).show()
            }else{
//                val intent = Intent(activity, SecondActivity::class.java)
//                intent.putExtra("email",usernameString)
//                intent.putExtra("pass",passString)
                val intent = Intent(activity, FeedActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }

            //NEED TO CHECK IF LOGIN IS IN DATABASE




        }

        return view
    }




}