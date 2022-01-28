package com.carlosribeiro.chessclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.IdRes

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rapid: Button = findViewById(R.id.rapid10)
        val classical: Button = findViewById(R.id.class30)
        val blitz: Button = findViewById(R.id.blitz5)
        val subtitle: TextView = findViewById(R.id.choose)


        blitz.setOnClickListener {
            val intent = Intent(this@MainActivity, ClockActivity::class.java)
            intent.putExtra("time","5")
            startActivity(intent)
        }
        rapid.setOnClickListener {
            val intent = Intent(this@MainActivity, ClockActivity::class.java)
            intent.putExtra("time","10")
            startActivity(intent)
        }
        classical.setOnClickListener {
            val intent = Intent(this@MainActivity, ClockActivity::class.java)
            intent.putExtra("time","30")
            startActivity(intent)
        }
    }

}