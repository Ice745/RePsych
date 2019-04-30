package com.example.repsych

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_payment.*

class Payment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
    }

    fun getValues(view: View){
        val amount = findViewById<EditText>(R.id.price_inp)

        var balance =  intent.extras.getFloat("balance")

        if (balance.toString() < amount.text.toString()){
            Toast.makeText(this, "Insufficient funds",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Purchase Successful",Toast.LENGTH_LONG).show()
        }

//        Toast.makeText(this, spinner2.selectedItem.toString() + amount.text,Toast.LENGTH_LONG).show()
    }
}
