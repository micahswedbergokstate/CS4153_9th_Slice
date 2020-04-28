package com.example.cs4153pizzaapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btnCreateAccount
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import java.math.BigInteger
import java.security.MessageDigest

class CreateAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        var currentUser = "Guest"

        // Attempt to store user credentials if:
        // 1. The user does not already exist
        // 2. The email address is valid
        // 3. The password is valid
        btnCreateAccount.setOnClickListener {
            val email = txtEmail.text.toString()                     // Email input by user
            val pass1 = txtPassword1.text.toString()                       // Password input by user
            val pass2 = txtPassword2.text.toString()                       // Password input by user
            val md5 = pass1.md5()                                        // Get the password hash
            var msg: String                                             // This will be the resulting output
            if (userExists(email)) {                                    // Can't create duplicate users
                msg = "That user already exists."
            } else if (pass1 != pass2) {
                msg = "Passwords do not match."
            } else if (isValidEmail(email) && isValidPassword(pass1)) {  // Email and password are valid?
                createUser(email, md5)                                  // Create the user!
                currentUser = email                                     // We now have a logged in user
                msg = "User created.\n"
                msg += "Welcome, $currentUser!"
            } else {                                                    // The credentials were invalid.
                msg = "Invalid credentials. Enter your email address and password.\n"
                msg += "Password requirements:\n"
                msg += " * 8-32 characters in length\n"
                msg += " * Can contain numbers\n"
                msg += " * Can contain uppercase letters\n"
                msg += " * Can contain lowercase letters\n"
            }
            //tvCreateOutput.text = msg                                   // Print the results
        }
    }

    // Check a string to see if it represents a valid email address. Actual existence of the email is not verified.
    fun isValidEmail(userInput: String): Boolean {
        return if (userInput.isBlank()) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(userInput).matches()
        }
    }

    // Check a string to see if it meets our requirements for a password:
    // 1. 8-32 characters in length
    // 2. Can contain numbers
    // 3. Can contain uppercase letters
    // 4. Can contain lowercase letters
    fun isValidPassword(userInput: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9]*$")      // Simple regex, modify password requirements here.
        return if (userInput.isBlank() || userInput.length < 8 || userInput.length > 32) {
            false
        } else {
            userInput.matches(regex)
        }
    }

    // Attempt to get a user's stored hash
    fun getStoredHash(email: String): String? {
        val data = getUserByEmail(email)                    // The user's email, used to lookup the record
        var result: String? = null                          // The resulting password hash, null if the email does not exist

        if (data == null || data.isEmpty()) {               // No data was returned from the record lookup, return null
            return null
        }

        var numFound = 0                                    // Should never be duplicates, but check anyway

        for (element in data) {                             // Check all the records returned
            if (element.first == email) {                   // The first element is the email, check it against our email parameter
                numFound += 1                               // Count it
                result = element.second                     // Result is the last matching record's hash
            }
        }

        return if (numFound == 1) {                         // If we found only one record, return that record's hash
            result
        } else {                                            // Otherwise, return null, can't return multiple hashes
            null
        }
    }

    // Check to see if a user exists
    fun userExists(searchEmail: String): Boolean {
        val data = getUserByEmail(searchEmail)      // Look up the record using the email field

        return !(data == null || data.isEmpty())    // No data means the record does not exist
    }

    // Attempt to get a record by looking up the email
    fun getUserByEmail(searchEmail: String): List<Pair<String, String>>? {
        var results: List<Pair<String, String>>? = null // The result
        database.use {
            results = select(DatabaseManager.TABLE_NAME)
                .whereArgs("${DatabaseManager.COLUMN_EMAIL} = {email}", "email" to searchEmail)                     // Lookup the email
                .parseList(rowParser { id: Int, username: String, password: String -> Pair(username, password) })   // We're returning a list of pairs
        }
        return results  // Return all the records, if any
    }

    // Create a user, assumes email and password have been validated
    fun createUser(email: String, pass: String) {
        database.use {
            insert(DatabaseManager.TABLE_NAME, DatabaseManager.COLUMN_EMAIL to email, DatabaseManager.COLUMN_PASSWORD to pass)
        }
    }

    // Get the md5 hash of a String
    fun String.md5(): String {
        val algorithm = MessageDigest.getInstance("MD5")
        return BigInteger(1, algorithm.digest(toByteArray())).toString(16).padStart(32, '0')
    }

}
