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

        cartText.text = "Cart: $" + Order.total.roundToInt().toString()

        //Drinks
        btnAddCoke.setOnClickListener {
            Order.addDrink("Coke")
            cokeCount.text = "  " + countItems("Coke") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnRemoveCoke.setOnClickListener {
            Order.removeDrink("Coke")
            cokeCount.text = "  " + countItems("Coke") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnAddDietCoke.setOnClickListener {
            Order.addDrink("Coke")
            cokeCount.text = "  " + countItems("Diet Coke") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnRemoveDietCoke.setOnClickListener {
            Order.removeDrink("Diet Coke")
            cokeCount.text = "  " + countItems("Diet Coke") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnAddSprite.setOnClickListener {
            Order.addDrink("Sprite")
            spriteCount.text = "  " + countItems("Sprite") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnRemoveSprite.setOnClickListener {
            Order.removeDrink("Sprite")
            spriteCount.text = "  " + countItems("Sprite") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnAddFanta.setOnClickListener {
            Order.addDrink("Fanta")
            fantaCount.text = "  " + countItems("Fanta") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnRemoveFanta.setOnClickListener {
            Order.removeDrink("Fanta")
            fantaCount.text = "  " + countItems("Fanta") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnAddDrPepper.setOnClickListener {
            Order.addDrink("Dr Pepper")
            drPepperCount.text = "  " + countItems("Dr Pepper") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnRemoveDrPepper.setOnClickListener {
            Order.removeDrink("Dr Pepper")
            drPepperCount.text = "  " + countItems("Dr Pepper") + "  "
            cartText.text = "Cart: $" + Order.total.toString()
        }

        btnReviewOrder.setOnClickListener {
            val intent = Intent(this, ReviewOrder::class.java)
            startActivity(intent)
        }

    }

    fun countItems(item: String): Int {
        var count = 0
        for(i in Order.drinks) {
            if (i.name==item) count++
        }
        return count
    }
}
