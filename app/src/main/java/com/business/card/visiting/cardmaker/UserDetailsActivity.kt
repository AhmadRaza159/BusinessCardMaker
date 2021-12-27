package com.business.card.visiting.cardmaker

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.android.synthetic.main.template_1.*

class UserDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        var sharedPref = this.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        user_name.setText(sharedPref.getString("user_name", ""))
        user_des.setText(sharedPref.getString("user_des", ""))
        user_phone.setText(sharedPref.getString("user_phone", ""))
        user_email.setText(sharedPref.getString("user_email", ""))
        user_web.setText(sharedPref.getString("user_web", ""))
        user_addr.setText(sharedPref.getString("user_addr", ""))
        user_company_name.setText(sharedPref.getString("user_company_name", ""))

        val editor: SharedPreferences.Editor = sharedPref.edit()



        user_save_button.setOnClickListener {

            if (!TextUtils.isEmpty(user_name.text.toString())) {
                editor.putString("user_name", user_name.text.toString())
                if (!TextUtils.isEmpty(user_des.text.toString())) {
                    editor.putString("user_des", user_des.text.toString())
                } else {
                    editor.putString("user_des", "Your Designation")

                }
                if (!TextUtils.isEmpty(user_phone.text.toString())) {
                    editor.putString("user_phone", user_phone.text.toString())
                } else {
                    editor.putString("user_phone", "Your Phone")

                }
                if (!TextUtils.isEmpty(user_web.text.toString())) {
                    editor.putString("user_web", user_web.text.toString())
                } else {
                    editor.putString("user_web", "Your Website")
                }
                if (!TextUtils.isEmpty(user_addr.text.toString())) {
                    editor.putString("user_addr", user_addr.text.toString())
                } else {
                    editor.putString("user_addr", "Your Address")
                }
                if (!TextUtils.isEmpty(user_email.text.toString())) {
                    editor.putString("user_email", user_email.text.toString())
                } else {
                    editor.putString("user_email", "Your Email")
                }
                if (!TextUtils.isEmpty(user_company_name.text.toString())) {
                    editor.putString("user_company_name", user_company_name.text.toString())
                } else {
                    editor.putString("user_company_name", "Company Name")
                }
                editor.apply()
                editor.commit()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                user_name.setError("Name should not be empty!")
                Toast.makeText(applicationContext, "Name Should not be empty", Toast.LENGTH_SHORT)
                    .show()
            }


        }

    }
}