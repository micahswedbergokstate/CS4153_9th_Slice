package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_select_drink.*
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class SelectDrink : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_drink)

        cartText.text = "Subtotal: $" + Order.subtotal.toString()

        //Drinks
        btnAddCoke.setOnClickListener {
            Order.addDrink("Coke")
            cokeCount.text = "  " + countItems("Coke") + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnRemoveCoke.setOnClickListener {
            Order.removeDrink("Coke")
            cokeCount.text = "  " + countItems("Coke") + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnAddDietCoke.setOnClickListener {
            Order.addDrink("Diet Coke")
            dietCokeCount.text = "  " + countItems("Diet Coke") + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnRemoveDietCoke.setOnClickListener {
            Order.removeDrink("Diet Coke")
            dietCokeCount.text = "  " + countItems("Diet Coke") + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnAddSprite.setOnClickListener {
            Order.addDrink("Sprite")
            spriteCount.text = "  " + countItems("Sprite") + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnRemoveSprite.setOnClickListener {
            Order.removeDrink("Sprite")
            spriteCount.text = "  " + countItems("Sprite") + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnAddFanta.setOnClickListener {
            Order.addDrink("Fanta")
            fantaCount.text = "  " + countItems("Fanta") + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnRemoveFanta.setOnClickListener {
            Order.removeDrink("Fanta")
            fantaCount.text = "  " + countItems("Fanta") + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnAddDrPepper.setOnClickListener {
            Order.addDrink("Dr Pepper")
            drPepperCount.text = "  " + countItems("Dr Pepper") + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnRemoveDrPepper.setOnClickListener {
            Order.removeDrink("Dr Pepper")
            drPepperCount.text = "  " + countItems("Dr Pepper") + "  "
            cartText.text = "Subtotal: $" + Order.subtotal.toString()
        }

        btnReviewOrder.setOnClickListener {
            val intent = Intent(this, ReviewOrder::class.java)
            startActivity(intent)
        }

    }

    fun countItems(item: String): Int {
        var count = 0
        for(i in Order.drinks) {
            if (i == item) count++
        }
        return count
    }
}
