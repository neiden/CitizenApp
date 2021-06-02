package com.example.citizen

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class SignUpTab : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.signup_fragment,container,false)

        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)
        val signUp : Button = view.findViewById(R.id.buttonSignUp)
        val name : EditText = view.findViewById(R.id.name)
        val username: EditText = view.findViewById(R.id.email)
        val pass: EditText = view.findViewById(R.id.pass)
        val badgeNum : EditText = view.findViewById(R.id.badgeNum)
        var currString = ""



        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = view.findViewById(checkedId)
            currString = radio.text as String
            if(radio.text == "Government Official"){
                Log.d("DEBUG","Officerrrrrr")
                badgeNum.setBackgroundResource(R.drawable.edit_text)
                badgeNum.hint = "Badge Number"
            }

            if(radio.text == "Civilian"){
                Log.d("DEBUG","Civiliannn")
                badgeNum.setBackgroundColor(Color.TRANSPARENT)
                badgeNum.hint =""
            }

        }



        signUp.setOnClickListener{

            val usernameString = username.text.toString()
            val passString = pass.text.toString()
            val nameString = name.text.toString()
            var BadgeNumString = ""

            if(currString == "Government Official"){
                BadgeNumString = badgeNum.text.toString()
            }else{
                BadgeNumString = "N/A"
            }

            if(usernameString.isEmpty() || passString.isEmpty() || nameString.isEmpty()){
                Toast.makeText(activity,"Enter Valid Credentials", Toast.LENGTH_LONG).show()
            }else{
//                val intent = Intent(activity, SecondActivity::class.java)
//                intent.putExtra("email",usernameString)
//                intent.putExtra("pass",passString)
//                intent.putExtra("name", nameString)
//                intent.putExtra("badge",BadgeNumString)
                val intent = Intent(activity, FeedActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }




        }

        return view
    }
}