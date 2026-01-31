package com.example.second_mad

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.Button
import android.widget.TextView
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val ci : Button = findViewById(R.id.button)
        val font:  TextView=findViewById(R.id.font)
        /**var fsize : Float = 10f
        val font:  TextView=findViewById(R.id.font)
        size.setOnClickListener {
            fsize=(fsize+10)%100
            font.textSize=fsize
        }**/

        val co:Button=findViewById(R.id.button1)
        val reset:Button=findViewById(R.id.button2)
        var count: Int=0
        ci.setOnClickListener {
                count=count+1
                font.text = "$count"
        }
        co.setOnClickListener {
            count-=1
            font.setText("$count")
        }
        reset.setOnClickListener {
            count=0
            font.text="$count"
        }

        }
    }

