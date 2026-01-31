package com.example.exp_1d

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


        val celc: Button=findViewById(R.id.b1)
        val fare: Button=findViewById(R.id.b2)
        val dele: Button=findViewById(R.id.b3)
        val temp: TextInputEditText=findViewById(R.id.input)
        val res: TextView=findViewById(R.id.result)

        celc.setOnClickListener {
            val t=temp.text.toString().toDouble()
            val t2=(t-32)*(5.0/9.0)
            res.text="conversion of celcius to Farenheit is:%.2f".format(t2)
        }
        fare.setOnClickListener {
            val t=temp.text.toString().toDouble()
            val t2=(t*(9.0/5.0))+32
            res.text="conversion of  Farenheit to celcius is:%.2f".format(t2)
        }
        dele.setOnClickListener {
            res.text="Enter something"
        }

    }
}
