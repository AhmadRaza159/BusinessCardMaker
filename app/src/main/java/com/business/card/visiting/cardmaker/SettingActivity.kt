package com.business.card.visiting.cardmaker

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    private lateinit var storagePermission: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        storagePermission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)


        back.setOnClickListener {
            finish()
        }

        setting_home.setOnClickListener {
            finish()
        }

        setting_share.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey check out this app at: https://play.google.com/store/apps/details?id=" + applicationContext.packageName
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }

        setting_user_detail.setOnClickListener {
            startActivity(Intent(this,UserDetailsActivity::class.java))
            finish()
        }

        setting_my_creation.setOnClickListener {
            if(checkStoragePermission()) {
                startActivity(Intent(this, MyCreationActivity::class.java))
            }
            else{
                requestStoragePermission()
            }
        }

        setting_rate.setOnClickListener {
            val uri = Uri.parse("market://details?id=" + applicationContext.packageName)
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            )
            try {
                startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + applicationContext.packageName)
                    )
                )
            }

        }

        setting_policy.setOnClickListener {
            startActivity(Intent(this,PrivacyPolicy::class.java))
        }

        setting_more.setOnClickListener {
            val uri =
                Uri.parse("https://play.google.com/store/apps/developer?id=Qasbalianapps")
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            )
            try {
                startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/developer?id=Qasbalianapps")
                    )
                )
            }
        }

        setting_exit.setOnClickListener {
            finishAffinity()
        }
    }
    fun requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, 11)
    }

    fun checkStoragePermission(): Boolean {
        var a= ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        var b= ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        return a&&b
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (checkStoragePermission()){
            startActivity(Intent(this, MyCreationActivity::class.java))
        }
    }
}