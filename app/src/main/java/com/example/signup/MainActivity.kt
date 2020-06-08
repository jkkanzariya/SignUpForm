package com.example.signup

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var edit_fname: EditText? = null
    var text_fname: TextInputLayout? = null
    var edit_lname: EditText? = null
    var text_lname: TextInputLayout? = null
    var edit_email: EditText? = null
    var text_email: TextInputLayout? = null
    var edit_pass: EditText? = null
    var text_pass: TextInputLayout? = null
    var btnRegister: Button? = null
    var btnRegister_login: Button? = null
    var radio_group: RadioGroup? = null
    var date: TextView? = null
    var calendar = Calendar.getInstance()
    var check_check1: CheckBox? = null
    var check_check2: CheckBox? = null
    var check_check3: CheckBox? = null
    var check_check4: CheckBox? = null

    var spinnerRunTime: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinnerRunTime = Spinner(this)
        findID()
        fillAge()
        onDOB()
        btnRegister_login?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnRegister?.setOnClickListener {

            if (!validationFname()) {
                return@setOnClickListener
            }
            if (!validationLname()) {
                return@setOnClickListener
            }
            if (!validationEmail()) {
                return@setOnClickListener
            }
            if (!validationPass()) {
                return@setOnClickListener
            }
            if (!validateCheckBox()) {
                return@setOnClickListener
            }
            if (spinnerRunTime!!.selectedItem.toString().trim() == "Select") {
                Toast.makeText(this, " Please select your age ", Toast.LENGTH_SHORT).show();
            }

            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("email", edit_email?.text.toString())
            intent.putExtra("password", edit_pass?.text.toString())
            startActivity(intent)
        }
    }

    private fun findID() {
        edit_fname = findViewById<EditText>(R.id.edit_fname)
        text_fname = findViewById<TextInputLayout>(R.id.text_fname)
        edit_lname = findViewById<EditText>(R.id.edit_lname)
        text_lname = findViewById<TextInputLayout>(R.id.text_lname)
        edit_email = findViewById<EditText>(R.id.edit_email)
        text_email = findViewById<TextInputLayout>(R.id.text_email)
        edit_pass = findViewById<EditText>(R.id.edit_pass)
        text_pass = findViewById<TextInputLayout>(R.id.text_pass)
        btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister_login = findViewById<Button>(R.id.btnRegister_login)
        radio_group = findViewById<RadioGroup>(R.id.radio_group)
        date = findViewById<TextView>(R.id.date)
        check_check1 = findViewById<CheckBox>(R.id.check_check1)
        check_check2 = findViewById<CheckBox>(R.id.check_check2)
        check_check3 = findViewById<CheckBox>(R.id.check_check3)
        check_check4 = findViewById<CheckBox>(R.id.check_check4)
    }

    private fun validationFname(): Boolean {
        if (edit_fname?.text.toString().trim { it <= ' ' }.isEmpty()) {
            edit_fname?.setError("First name can't be empty.")
            return false
        } else {
            text_fname!!.isErrorEnabled = false
        }
        return true
    }

    private fun validationLname(): Boolean {
        if (edit_lname?.text.toString().trim { it <= ' ' }.isEmpty()) {
            edit_lname?.setError("Last name can't be empty.")
            return false
        } else {
            text_lname!!.isErrorEnabled = false
        }
        return true
    }

    private fun validationEmail(): Boolean {
        val email = edit_email?.text.toString().trim { it <= ' ' }
        if (email.isEmpty() || !isValidEmail(email)) {
            edit_email?.setError("Follow the email format.")
            return false
        } else {
            text_email!!.isErrorEnabled = false
        }
        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validationPass(): Boolean {
        if (edit_pass?.text.toString().trim { it <= ' ' }.isEmpty()) {
            edit_pass?.setError("Enter password.")
            return false
        } else {
            text_pass!!.isErrorEnabled = false
        }
        return true
    }

    private fun fillAge() {
        val agelist = resources.getStringArray(R.array.AgeList)
        //        val v:View=spinnerRunTime.selectedView

        spinnerRunTime!!.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val linearLayout = findViewById<LinearLayout>(R.id.age)
        linearLayout.addView(spinnerRunTime)

        val fill_age = ArrayAdapter(this, android.R.layout.simple_spinner_item, agelist)
        spinnerRunTime!!.adapter = fill_age

    }

    fun radioButtonClick(view: View) {
        val radio: RadioButton? = radio_group?.checkedRadioButtonId?.let { findViewById(it) }
        toast("${radio?.text}")
    }

    fun checkBoxClicked(view: View) {
        val checked = view as CheckBox
        if (check_check1 == checked) {
            message(check_check1?.text.toString() + if (check_check1?.isChecked!!) " Checked " else " UnChecked ")
        }
        if (check_check2 == checked) {
            message(check_check2?.text.toString() + if (check_check2?.isChecked!!) " Checked " else " UnChecked ")
        }
        if (check_check3 == checked) {
            message(check_check3?.text.toString() + if (check_check3?.isChecked!!) " Checked " else " UnChecked ")
        }
        if (check_check4 == checked) {
            message(check_check4?.text.toString() + if (check_check4?.isChecked!!) " Checked " else " UnChecked ")
        }
    }

    private fun message(controlID: String) {
        toast(controlID)
    }

    private fun validateCheckBox(): Boolean {
        if (!(check_check1?.isChecked!! || check_check2?.isChecked!! || check_check3?.isChecked!! || check_check4?.isChecked!!)) {
            toast("Select atleast one check box ")
            return false
        }
        return true
    }

    private fun onDOB() {
        val dateListenerDialog =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        date?.setOnClickListener {
            DatePickerDialog(
                this@MainActivity,
                dateListenerDialog,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateDateInView() {
        val dateFormat = "dd/MM/yyyy"
        val simpleFormat = SimpleDateFormat(dateFormat, Locale.US)
        date?.text = (simpleFormat.format(calendar.time))
    }
}