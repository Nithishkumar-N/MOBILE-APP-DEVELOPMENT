package com.example.exp_2a

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.util.*
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentExpression = ""
    private val formatter = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)
        val grid = findViewById<GridLayout>(R.id.buttonGrid)

        for (i in 0 until grid.childCount) {
            val view = grid.getChildAt(i)
            if (view is Button) {
                view.setOnClickListener {
                    handleButtonClick(view.text.toString())
                }
            }
        }
    }

    private fun handleButtonClick(text: String) {
        when (text) {

            "C" -> {
                currentExpression = ""
                display.text = "0"
            }

            "=" -> {
                try {
                    val result = evaluate(currentExpression)

                    if (result.isNaN() || result.isInfinite()) {
                        display.text = "Error"
                        return
                    }

                    val cleanResult =
                        (result * 100.0).roundToInt() / 100.0

                    val formatted = formatter.format(cleanResult)

                    display.text = formatted
                    currentExpression = formatted

                } catch (e: Exception) {
                    display.text = "Error"
                }
            }

            "SIN" -> appendFunction("sin(")
            "COS" -> appendFunction("cos(")
            "TAN" -> appendFunction("tan(")
            "LOG" -> appendFunction("log(")
            "√" -> appendFunction("sqrt(")

            else -> {
                currentExpression += text
                display.text = currentExpression
            }
        }
    }

    private fun appendFunction(func: String) {
        currentExpression += func
        display.text = currentExpression
    }

    // =========================
    // SCIENTIFIC ENGINE
    // =========================

    private fun evaluate(expression: String): Double {
        val tokens = tokenize(expression)
        val rpn = toRPN(tokens)
        return evaluateRPN(rpn)
    }

    private fun tokenize(expr: String): List<String> {
        val tokens = mutableListOf<String>()
        var i = 0

        while (i < expr.length) {
            val c = expr[i]

            if (c.isDigit() || c == '.') {
                var number = ""
                while (i < expr.length &&
                    (expr[i].isDigit() || expr[i] == '.')
                ) {
                    number += expr[i]
                    i++
                }
                tokens.add(number)
                continue
            }

            if (c.isLetter()) {
                var func = ""
                while (i < expr.length &&
                    expr[i].isLetter()
                ) {
                    func += expr[i]
                    i++
                }
                tokens.add(func)
                continue
            }

            tokens.add(c.toString())
            i++
        }

        return tokens
    }

    private fun toRPN(tokens: List<String>): List<String> {
        val output = mutableListOf<String>()
        val stack = Stack<String>()

        val precedence = mapOf(
            "+" to 1,
            "-" to 1,
            "*" to 2,
            "/" to 2
        )

        for (token in tokens) {
            when {
                token.toDoubleOrNull() != null -> output.add(token)

                token in listOf("sin", "cos", "tan", "log", "sqrt") ->
                    stack.push(token)

                token in precedence.keys -> {
                    while (stack.isNotEmpty() &&
                        precedence.containsKey(stack.peek()) &&
                        precedence[stack.peek()]!! >= precedence[token]!!
                    ) {
                        output.add(stack.pop())
                    }
                    stack.push(token)
                }

                token == "(" -> stack.push(token)

                token == ")" -> {
                    while (stack.peek() != "(") {
                        output.add(stack.pop())
                    }
                    stack.pop()

                    if (stack.isNotEmpty() &&
                        stack.peek() in listOf(
                            "sin", "cos", "tan",
                            "log", "sqrt"
                        )
                    ) {
                        output.add(stack.pop())
                    }
                }
            }
        }

        while (stack.isNotEmpty()) {
            output.add(stack.pop())
        }

        return output
    }

    private fun evaluateRPN(rpn: List<String>): Double {
        val stack = Stack<Double>()

        for (token in rpn) {
            when {
                token.toDoubleOrNull() != null ->
                    stack.push(token.toDouble())

                token == "+" -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(a + b)
                }

                token == "-" -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(a - b)
                }

                token == "*" -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(a * b)
                }

                token == "/" -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(a / b)
                }

                token == "sin" ->
                    stack.push(sin(Math.toRadians(stack.pop())))

                token == "cos" ->
                    stack.push(cos(Math.toRadians(stack.pop())))

                token == "tan" ->
                    stack.push(tan(Math.toRadians(stack.pop())))

                token == "log" ->
                    stack.push(log10(stack.pop()))

                token == "sqrt" ->
                    stack.push(sqrt(stack.pop()))
            }
        }

        return stack.pop()
    }
}