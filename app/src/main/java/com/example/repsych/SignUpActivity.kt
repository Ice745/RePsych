package com.example.repsych

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties

class SignUpActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()

    var fbAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        val register = findViewById<Button>(R.id.signup_btn)
        val email = findViewById<EditText>(R.id.email_in)
        val pass = findViewById<EditText>(R.id.pass_in)
        val cpass = findViewById<EditText>(R.id.cpass_in)
        register.setOnClickListener {view ->
            if (pass.text.toString() == cpass.text.toString()){
                signUp(view, email.text.toString(),pass.text.toString())
                saveUser()
            }else{
                Snackbar.make(view, "Passwords do not match", Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
            }
        }
    }

    fun signUp(view: View, email: String, password: String){
        showMessage(view,"Authenticating...")

        fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->
            if(task.isSuccessful){
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("id", fbAuth.currentUser?.email)
                startActivity(intent)

            }else{
                showMessage(view,"Error: ${task.exception?.message}")
            }
        }
    }

    fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }



    fun saveUser(){
        val email = findViewById<EditText>(R.id.email_in)
        val pass = findViewById<EditText>(R.id.pass_in)
        val myRef = database.getReference("users")
//        val userId =
        val users = Users(email.text.toString(),pass.text.toString(),0.0, listOf())


        myRef.child("Users").setValue(users)


    }
}
