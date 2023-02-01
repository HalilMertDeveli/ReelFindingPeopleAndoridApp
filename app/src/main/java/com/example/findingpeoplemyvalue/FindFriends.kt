package com.example.findingpeoplemyvalue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_find_friends.*

class FindFriends : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_friends)

        val user = Firebase.auth.currentUser

        if (user != null){
            userNameTextView.text = user.email.toString()
        }
        else{
            println("user is not here")
        }

    }

}