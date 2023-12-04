package com.example.downloadservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun stop(view: View) {
        Intent(applicationContext, DownloadService::class.java).also {
            stopService(it)
        }
    }
    fun download(view: View) {
        Intent(applicationContext, DownloadService::class.java).also {
            startService(it)
        }
    }
}