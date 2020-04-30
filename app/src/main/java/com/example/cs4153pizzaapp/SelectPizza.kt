package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_select_pizza.*

class SelectPizza : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_pizza)

        btnSideSelect.setOnClickListener {
            val intent = Intent(this, SelectSides::class.java)
            startActivity(intent)
        }
    }
}
