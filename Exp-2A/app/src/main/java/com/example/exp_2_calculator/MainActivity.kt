package com.example.exp_2_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val v1: EditText=findViewById(R.id.input1)
        val v2: EditText=findViewById(R.id.input2)
        val  res: EditText=findViewById(R.id.input3)
        val add: Button=findViewById(R.id.add)
        val sub: Button=findViewById(R.id.sub)
        val mul: Button=findViewById(R.id.mul)
        val mod: Button=findViewById(R.id.mod)
        val div: Button=findViewById(R.id.div)


        add.setOnClickListener {
            val x=v1.text.toString().toDouble()
            val y=v2.text.toString().toDouble()
            val z=x+y
            res.setText(z.toString())
        }
        sub.setOnClickListener {
            val x=v1.text.toString().toDouble()
            val y=v2.text.toString().toDouble()
            val z=x-y
            res.setText(z.toString())
        }
        mul.setOnClickListener {
            val x=v1.text.toString().toDouble()
            val y=v2.text.toString().toDouble()
            val z=x*y
            res.setText(z.toString())
        }
        mod.setOnClickListener {
            val x=v1.text.toString().toDouble()
            val y=v2.text.toString().toDouble()
            val z=x%y
            res.setText(z.toString())
        }
        div.setOnClickListener {
            val x=v1.text.toString().toDouble()
            val y=v2.text.toString().toDouble()
            val z=x/y
            res.setText(z.toString())
        }
    }
}
