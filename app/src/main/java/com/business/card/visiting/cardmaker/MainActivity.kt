package com.business.card.visiting.cardmaker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener


class MainActivity : AppCompatActivity() {
    private lateinit var card1:ImageButton
    private lateinit var card2:ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        Log.d("LogKey", " type")
        home_appbarlayout.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                home_collapsing_toolbar.title="Business & Visiting Card maker"
                home_setting.setImageDrawable(resources.getDrawable(R.drawable.menu_white))
                home_collapsing_toolbar.animate().rotation(0f).duration=500
                home_setting.animate().rotation(180f).duration=500
//
//                val a: Animation =
//                    AnimationUtils.loadAnimation(this, R.anim.fade_out)
//                a.duration = 250
//                home_setting.startAnimation(a)
//                Handler().postDelayed({
//
//
//                    val a1: Animation =
//                        AnimationUtils.loadAnimation(this, R.anim.fade_in)
//                    a1.duration = 250
//                    home_setting.startAnimation(a1)
//
//                }, 250)

            } else {
                home_collapsing_toolbar.title=""
                home_setting.setImageDrawable(resources.getDrawable(R.drawable.menu_black))
                home_collapsing_toolbar.animate().rotation(0f).duration=500
                home_setting.animate().rotation(0f).duration=500
//                val a: Animation =
//                    AnimationUtils.loadAnimation(this, R.anim.fade_out)
//                a.duration = 250
//                home_setting.startAnimation(a)
//                Handler().postDelayed({
//
//
//                    val a1: Animation =
//                        AnimationUtils.loadAnimation(this, R.anim.fade_in)
//                    a1.duration = 250
//                    home_setting.startAnimation(a1)
//                }, 250)


            }
        })

        home_setting.setOnClickListener{
            startActivity(Intent(this,SettingActivity::class.java))
        }

        card1=findViewById(R.id.card_temp_1)
        card2=findViewById(R.id.card_temp_2)


        card1.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",1)
            startActivity(intent)
        }
        card2.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",2)
            startActivity(intent)
        }
        card_temp_3.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",3)
            startActivity(intent)
        }
        card_temp_4.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",4)
            startActivity(intent)
        }
        card_temp_5.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",5)
            startActivity(intent)
        }
        card_temp_6.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",6)
            startActivity(intent)
        }
        card_temp_7.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",7)
            startActivity(intent)
        }
        card_temp_8.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",8)
            startActivity(intent)
        }
        card_temp_9.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",9)
            startActivity(intent)
        }
        card_temp_10.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",10)
            startActivity(intent)
        }
        card_temp_11.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",11)
            startActivity(intent)
        }
        card_temp_12.setOnClickListener {
            var intent =Intent(this,EditCardActivity::class.java)
            intent.putExtra("code",12)
            startActivity(intent)
        }
    }
}