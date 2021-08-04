package com.nextgen.scanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.nextgen.scanner.activity.PPCaptureActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnQR = findViewById<View>(R.id.btnQR) as Button
        btnQR.setOnClickListener {
            startActivity(Intent(this, PPCaptureActivity::class.java))
        }
    }
}