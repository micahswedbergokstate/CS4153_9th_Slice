package com.example.cs4153pizzaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_review_order.*
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class ReviewOrder : AppCompatActivity() {

    val senderEmail = "ninthslice@gmail.com"
    val senderPassword = "password123*"
    lateinit var appExecutors: AppExecutors

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_order)
        appExecutors = AppExecutors()

        btnHome.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

        btnCheckout.setOnClickListener {
            val intent = Intent(this, Checkout::class.java)
            startActivity(intent)
            val properties = System.getProperties()
            properties["mail.smtp.auth"] = "true"
            properties["main.smtp.starttls.enable"] = "true"
            properties["main.smtp.host"] = "smtp.gmail.com"
            properties["main.smtp.port"] = "587"

            val session = Session.getInstance(properties, object : javax.mail.Authenticator() {
                override fun getPasswordAuthentication():
                        PasswordAuthentication {
                            return PasswordAuthentication(senderEmail, senderPassword)

                }
            })

            try {
                val message = MimeMessage(session)
                val emailID = "ninthslice@gmail.com"

                message.setFrom(InternetAddress(senderEmail))
                message.addRecipient(Message.RecipientType.TO, InternetAddress(emailID))

                message.subject = "Order Confirmation: 9th Slice Pizza"

                message.setText("Thank you for your order!")

                Transport.send(message)
                appExecutors.mainThread().execute {

                }

            } catch (e: MessagingException) {
                e.printStackTrace()
            }

        }
    }
}


