package com.example.cs4153pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_main.btnCreateAccount

class CreateAccount : AppCompatActivity() {
    val BACK_BUTTON_TAPPED = 0
    val ACCOUNT_CREATED = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Attempt to store user credentials if:
        // 1. The user does not already exist
        // 2. The email address is valid
        // 3. The password is valid
        btnCreateAccount.setOnClickListener {
            val email = txtEmail.text.toString()        // Email input by user
            val pass1 = txtPassword1.text.toString()    // Password input by user
            val pass2 = txtPassword2.text.toString()    // Password input by user
            var msg: String                             // This will be the resulting output
            // Can't create duplicate users
            if (AccountManager.userExists(database, email)) {
                msg = "That user already exists."
            } else if (pass1 != pass2) {
                // Password must be typed twice, correctly
                msg = "Passwords do not match."
            } else if (AccountManager.isValidEmail(email) && AccountManager.isValidPassword(pass1)) {
                // Email and password are valid
                val md5 = AccountManager.stringToMD5(pass1) // Get the password hash
                // Create the user!
                AccountManager.createUser(database, email, md5)
                txtEmail.setText("")
                txtPassword1.setText("")
                txtPassword2.setText("")
                // We now have a logged in user
                AccountManager.userType = AccountManager.AccountType.USER
                AccountManager.currentUser = email
                msg = "Account created!\n\n"
                msg += "Welcome, ${AccountManager.currentUser}!"

                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            } else {
                // The credentials were invalid.
                msg = "Invalid credentials. Enter your email address and password.\n\n"
                msg += "Password requirements:\n"
                msg += " * 8-32 characters in length\n"
                msg += " * Can contain numbers\n"
                msg += " * Can contain uppercase letters\n"
                msg += " * Can contain lowercase letters\n"
            }
            tvOutput.text = msg                                   // Print the results
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return super.onSupportNavigateUp()
    }
}
