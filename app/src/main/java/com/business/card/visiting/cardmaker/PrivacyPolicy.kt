package com.business.card.visiting.cardmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebResourceError

import android.webkit.WebResourceRequest

import android.annotation.TargetApi
import android.os.Build

import android.widget.Toast

import android.webkit.WebViewClient
import android.app.ProgressDialog







class PrivacyPolicy : AppCompatActivity() {
    lateinit var webView:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
        val pd = ProgressDialog(this)
        pd.setMessage("loading")
        pd.show()
        webView=findViewById(R.id.privacy_policy_web_view)
        webView.setWebViewClient(object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                Toast.makeText(applicationContext, description, Toast.LENGTH_SHORT).show()
            }
            @TargetApi(Build.VERSION_CODES.M)
            override fun onReceivedError(
                view: WebView,
                req: WebResourceRequest,
                rerr: WebResourceError
            ) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(
                    view,
                    rerr.errorCode,
                    rerr.description.toString(),
                    req.url.toString()
                )
            }
        })
        webView.loadUrl("https://qasbalianappsprivacypolicy.blogspot.com/2020/04/privacy-policy.html")

        webView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                pd.dismiss()
            }
        })


        //
    }
}