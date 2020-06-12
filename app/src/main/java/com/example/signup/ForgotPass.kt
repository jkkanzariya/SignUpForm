/*
package com.example.signup

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ForgotPass : AppCompatActivity() {

    var edit_email_forgot: EditText? = null
    var edit_pass_first: EditText? = null
    var edit_pass_second: EditText? = null
    var login_forgot: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpass)

        edit_email_forgot=findViewById(R.id.edit_email_forgot)
        edit_pass_first=findViewById(R.id.edit_pass_first)
        edit_pass_second=findViewById(R.id.edit_pass_second)
        login_forgot=findViewById(R.id.login_forgot)

        edit_email_forgot?.setText(intent.extras?.getString("email"))

        login_forgot?.setOnClickListener {
            if (edit_pass_first?.text.toString() == edit_pass_second?.text.toString()) {
                val alertBuilder = AlertDialog.Builder(this)
                alertBuilder.setMessage("Your password was changed!").setPositiveButton("OK") { dialog, which -> finish() }
                val alert = alertBuilder.create()
                alert.show()
            } else {
                toast("Password didn't match")
            }
        }
    }
}*/
