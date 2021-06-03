package com.example.citizen

import android.content.Context
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
import com.example.citizen.models.User

class LoginTab(var main: Context) : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.login_fragment,container,false)


        val username: EditText = view.findViewById(R.id.logUsername)
        val pass: EditText = view.findViewById(R.id.logPass)


        val btnLogin: Button = view.findViewById(R.id.buttonLogin)
        btnLogin.setOnClickListener{

            val usernameString = username.text.toString()
            val passString = pass.text.toString()

            Log.d("DEBUG", usernameString)
            Log.d("DEBUG",passString)
            Log.d("DEBUG","BUTTON PRESS")

            val database: Database = Database(main)
            val user: User = User(null,usernameString,passString,"",null)
            database.login(user) {
                if (it == null){
                    Toast.makeText(activity,"Enter Valid Login Credentials",Toast.LENGTH_LONG).show()
                } else {
                    val intent = Intent(activity, FeedActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
            }
            //Im checking if the user response is valid in the if statement below
            //Obviously userResponse.id == 1 is not the correct check; Just a placeholder
        }

        return view
    }




}