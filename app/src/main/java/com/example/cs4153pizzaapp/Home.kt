package com.example.cs4153pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.toast

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        cartText.text = "Cart: $" + Order.total.toString()

        if (AccountManager.getCurrentUserType() == AccountManager.UserType.USER) {
            btnLogin.isVisible = false
            btnLogout.isVisible = true
        } else {
            btnLogin.isVisible = true
            btnLogout.isVisible = false
        }

        // Attempt to sign out the user if
        // 1. A user is currently logged in
        btnLogout.setOnClickListener {
            btnLogin.isVisible = true
            btnLogout.isVisible = false

            AccountManager.setUser(null)

            toast("Logged out")
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnAbout.setOnClickListener {
            val intent = Intent(this, About::class.java)
            startActivity(intent)
        }

        btnOrderPizza.setOnClickListener {
            val intent = Intent(this, SelectPizza::class.java)
            startActivity(intent)
        }

        btnOrderEntree.setOnClickListener {
            val intent = Intent(this, SelectSides::class.java)
            startActivity(intent)
        }
    }
}
