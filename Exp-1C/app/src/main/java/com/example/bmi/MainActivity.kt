package com.example.bmi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val textresult: TextView=findViewById(R.id.text11)
        val weight: TextInputEditText=findViewById(R.id.bweight)
        val height: TextInputEditText=findViewById(R.id.eheight)
        val cal: Button=findViewById(R.id.button1)
        val clr: Button=findViewById(R.id.button2)

        cal.setOnClickListener {
            val wt=weight.text.toString().toDouble()
            val ht=height.text.toString().toDouble()
            val htm=ht/100.0
            val bmi=wt/(htm*htm)
            textresult.text="Result is BMI of:%.2f".format(bmi)
        }
        clr.setOnClickListener {
            weight.text?.clear()
            height.text?.clear()
            textresult.text="Hello, enter something"
        }
    }
}