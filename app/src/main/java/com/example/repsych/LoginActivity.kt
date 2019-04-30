package com.example.repsych

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login3)
        var email = findViewById<EditText>(R.id.emailText)
//        val email = emailText.toString()
        val password = findViewById<EditText>(R.id.passwordTxt)
        val mains = findViewById<Button>(R.id.login_btn)
        mains.setOnClickListener {view ->
            signIn(view,email.text.toString(),password.text.toString())
        }

    }

    fun signIn(view: View,email: String, password: String){
        showMessage(view,"Authenticating...")

        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if(task.isSuccessful){
                val intent = Intent(this, barcode::class.java)
                intent.putExtra("id", fbAuth.currentUser?.email)
                startActivity(intent)

            }else{
                showMessage(view,"Error: ${task.exception?.message}")
            }
        })

    }

    fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

}
