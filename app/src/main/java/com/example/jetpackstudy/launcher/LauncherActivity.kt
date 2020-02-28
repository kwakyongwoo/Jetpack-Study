package com.example.jetpackstudy.launcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpackstudy.R
import com.example.jetpackstudy.main.MainActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }
}
