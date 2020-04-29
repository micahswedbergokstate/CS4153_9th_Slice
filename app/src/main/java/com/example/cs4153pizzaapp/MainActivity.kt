package com.example.cs4153pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Attempt to sign in if:
        // 1. No user is already logged in
        // 2. Email is Valid
        // 3. Password is valid and the hash matches the hash in the database
        btnLogin.setOnClickListener {
            val email = txtLoginEmail.text.toString()       // Email input by user
            val pass = txtLoginPassword.text.toString()     // Password input by user
            val hashedPass: String?                         // The stored password hash
            var msg: String                                 // This will be the resulting output
            if (AccountManager.currentUser != "Guest") {
                // Cannot login if a user is already logged in
                msg = "You must log out first."
            } else if (AccountManager.isValidEmail(email) && AccountManager.isValidPassword(pass)) {
                // Email and password are valid
                // Get the stored has for the email address trying to login
                hashedPass = AccountManager.getStoredHash(database, email)
                if (hashedPass == AccountManager.stringToMD5(pass)) {
                    // If the input password hash matches the stored password hash
                    AccountManager.currentUser = email
                    // We now have a logged in user
                    msg = "You are logged in!\n"
                    msg += "Welcome, ${AccountManager.currentUser}!"
                } else {
                    // The passwords did not match
                    msg = "Password does not match our records."
                }
            } else {
                // The credentials were invalid
                msg = "Invalid credentials."
            }
            //tvSignInOutput.text = msg                                   // Print the results
        }

        btnCreateAccount.setOnClickListener {
            val intent = Intent(this, CreateAccount::class.java)
            
            startActivity(intent)
        }

        btnBeGuest.setOnClickListener {
            AccountManager.currentUser = "Guest"
            AccountManager.userType = AccountManager.AccountType.GUEST

            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

    }
}
