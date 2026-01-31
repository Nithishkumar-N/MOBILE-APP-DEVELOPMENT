package com.example.exp_3

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val text: TextView = findViewById(R.id.t)
        val but1: Button = findViewById(R.id.b1)
        val but2: Button = findViewById(R.id.b2)
        val but3: Button = findViewById(R.id.b3)
        val lay: LinearLayout = findViewById(R.id.main)

        var count = 10f
        var c = 0

        // Increase text size
        but1.setOnClickListener {
            count += 1f
            text.textSize = count
        }

        // Change text color
        but2.setOnClickListener {
            c++
            when (c % 4) {
                0 -> text.setTextColor(Color.GREEN)
                1 -> text.setTextColor(Color.CYAN)
                2 -> text.setTextColor(Color.RED)
                3 -> text.setTextColor(Color.YELLOW)
            }
        }

        // Change background color
        but3.setOnClickListener {
            c++
            when (c % 4) {
                0 -> lay.setBackgroundColor(Color.GREEN)
                1 -> lay.setBackgroundColor(Color.BLUE)
                2 -> lay.setBackgroundColor(Color.CYAN)
                3 -> lay.setBackgroundColor(Color.YELLOW)
            }
        }
    }
}
