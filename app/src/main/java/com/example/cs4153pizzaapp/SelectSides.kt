package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_select_sides.*


class SelectSides : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_sides)

        btnDrinkSelect.setOnClickListener {
            val intent = Intent(this, SelectDrink::class.java)
            startActivity(intent)
        }
    }
}
