package com.example.datavisualization

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var spinner: Spinner
    private lateinit var editTextValue: TextInputEditText
    private lateinit var button: Button

    private val categories = arrayOf("Food", "Transport", "Rent", "Utilities", "Other")
    private val values = mutableMapOf<String, Float>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        barChart = findViewById(R.id.barChart)
        spinner = findViewById(R.id.spinner)
        editTextValue = findViewById(R.id.editTextValue)
        button = findViewById(R.id.button)

        // Initialize values
        categories.forEach { values[it] = 0f }

        // Setup Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        setupChart()

        button.setOnClickListener {
            updateData()
        }
    }

    private fun setupChart() {
        barChart.description.isEnabled = false
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(categories)
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.granularity = 1f
        barChart.axisLeft.axisMinimum = 0f
        barChart.axisRight.isEnabled = false
        updateChart()
    }

    private fun updateData() {
        val selectedCategory = spinner.selectedItem.toString()
        val valueString = editTextValue.text.toString()

        if (valueString.isNotEmpty()) {
            val value = valueString.toFloatOrNull()
            if (value != null) {
                values[selectedCategory] = value
                updateChart()
                editTextValue.text?.clear()
            } else {
                Toast.makeText(this, "Invalid value", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateChart() {
        val entries = ArrayList<BarEntry>()
        categories.forEachIndexed { index, category ->
            entries.add(BarEntry(index.toFloat(), values[category] ?: 0f))
        }

        val dataSet = BarDataSet(entries, "Expenses")
        val data = BarData(dataSet)
        barChart.data = data
        barChart.invalidate() // refresh
    }
}
