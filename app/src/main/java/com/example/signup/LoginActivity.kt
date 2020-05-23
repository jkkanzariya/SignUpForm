package com.example.signup

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var edit_login_email: EditText? = null
    var edit_login_pass: EditText? = null
    var login_login: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edit_login_email = findViewById<EditText>(R.id.edit_login_email)
        edit_login_pass = findViewById<EditText>(R.id.edit_login_pass)
        login_login = findViewById<Button>(R.id.login_login)

        edit_login_email?.setText(intent.extras?.getString("email"))
        edit_login_pass?.setText(intent.extras?.getString("password"))

        login_login?.setOnClickListener {
            if (!validationEmail()) {
                return@setOnClickListener
            }
            if (!validationPass()) {
                return@setOnClickListener
            }
            val alertBuilder = AlertDialog.Builder(this)
            alertBuilder.setMessage("Your login is successful!").setPositiveButton(
                "OK",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            val alert = alertBuilder.create()
            alert.show()
        }
    }

    private fun validationEmail(): Boolean {
        val email = edit_login_email?.text.toString().trim { it <= ' ' }
        if (email.isEmpty() || !isValidEmail(email)) {
            edit_login_email?.setError("Follow the email format.")
            return false
        } else {
            text_login_email!!.isErrorEnabled = false
        }
        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validationPass(): Boolean {
        if (edit_login_pass?.text.toString().trim { it <= ' ' }.isEmpty()) {
            edit_login_pass?.setError("Enter password.")
            return false
        } else {
            text_login_password!!.isErrorEnabled = false
        }
        return true
    }
}
