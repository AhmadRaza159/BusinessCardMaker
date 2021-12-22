package com.business.card.visiting.cardmaker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val handler = Handler()
        handler.postDelayed({
            var sharedPref = this.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE)

            if (sharedPref.all.isEmpty()){
                startActivity(Intent(this,UserDetailsActivity::class.java))
                finish()
            }
            else{
                var intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }


        }, 1000)
    }
}