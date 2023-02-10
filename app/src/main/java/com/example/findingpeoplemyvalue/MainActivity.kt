package com.example.findingpeoplemyvalue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var authInstance: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authInstance = FirebaseAuth.getInstance()
        val activeUser = authInstance.currentUser

        if (activeUser != null) {
            val intent = Intent(applicationContext, GoogleMap::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun signUp(view: View) {

        val email = userEmail.text.toString().trim()
        val password = userPassword.text.toString().trim()

        if (email == "" && password == "") {
            authInstance.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, GoogleMap::class.java)
                        startActivity(intent)
                        finish()
                    }
                }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }


    }

    fun login(view: View) {
        val email = userEmail.text.toString().trim()
        val password = userPassword.text.toString().trim()


        if (email == "" && password == "") {
            authInstance.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (task.isSuccessful) {
                        val intent = Intent(this, GoogleMap::class.java)
                        startActivity(intent)
                        finish()
                    }


                    val currentUser = authInstance.currentUser
                    Toast.makeText(
                        applicationContext,
                        "Welcome ${currentUser?.email}",
                        Toast.LENGTH_LONG
                    ).show()


                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }


    }
}


