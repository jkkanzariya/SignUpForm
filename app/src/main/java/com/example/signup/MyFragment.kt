package com.example.signup

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

var edit_email_forgot:EditText?=null

class MyFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.activity_forgotpass, container, false)
        edit_email_forgot = rootView.findViewById<EditText>(R.id.edit_email_forgot)
        val edit_pass_first = rootView.findViewById<EditText>(R.id.edit_pass_first)
        val edit_pass_second = rootView.findViewById<EditText>(R.id.edit_pass_second)
        val login_forgot = rootView.findViewById<Button>(R.id.login_forgot)

        val bundle=arguments
        edit_email_forgot?.setText((bundle?.getString("email")))        //Done

        login_forgot?.setOnClickListener {
            val alertBuilder = AlertDialog
                .Builder(context)
            val alert = alertBuilder.create()
            if (edit_pass_first?.text.toString() == edit_pass_second?.text.toString()) {
                alertBuilder.setMessage("Your password was changed!").setPositiveButton("OK") { dialog, which -> dismiss() }
                alert.show()
            } else {
                alertBuilder.setMessage("Password didn't match").setNegativeButton("Cancel"){ dialog, which -> dismiss() }
                alert.show()
            }
        }
        return rootView
    }
}