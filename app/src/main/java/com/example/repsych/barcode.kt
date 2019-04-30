package com.example.repsych

import android.support.v7.app.AppCompatActivity
import android.app.Activity
import android.content.Intent
import android.widget.Button
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.synthetic.main.activity_barcode.*



class barcode : AppCompatActivity() {


    var places = listOf<String>()
    var balance = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)

        val scanBtn = findViewById<Button>(R.id.scan_button)
        balance_txt.text = "GHS $balance"

        val myListAdapter = MyListAdapter(this, places)
        _completeList.adapter = myListAdapter


        val register = findViewById<Button>(R.id.pay_btn)
        register.setOnClickListener {
            val intent = Intent(this, Payment::class.java)
            intent.putExtra("balance", balance)
            startActivity(intent)
        }

        scanBtn.setOnClickListener{
            val scanner = IntentIntegrator(this)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                } else {
                    if (places.contains(result.contents) == false){
                        Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                        places += result.contents
                        val myListAdapter = MyListAdapter(this, places)
                        _completeList.adapter = myListAdapter
                        balance += 0.2
                    }else{
                        Toast.makeText(this, "Scanned: This item has already been scanned" , Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }


}

