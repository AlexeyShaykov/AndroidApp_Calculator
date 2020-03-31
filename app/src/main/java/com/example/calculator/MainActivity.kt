package com.example.calculator

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_AC.setOnClickListener{ clearTextFields() }

        val btns = listOf<TextView>(btn_0, btn_1, btn_2, btn_3, btn_4,
                                    btn_5, btn_6, btn_7, btn_8, btn_9,
                                    btn_leftS, btn_rightS, btn_point,
                                    btn_plus, btn_division, btn_multiple, btn_minus)
        btns.forEach{
            val btnValue : String = it.text.toString()
            it.setOnClickListener{ setTextFields(btnValue) }
        }

        btn_equal.setOnClickListener{ setTextResult(math_operation.text.toString()) }

        btn_back.setOnClickListener{
            val str = math_operation.text.toString()

            if (str.isNotEmpty()) {
                math_operation.text = math_operation.text.substring(0, math_operation.text.length - 1)
            }

            result.text = ""
        }

    }

    fun isInteger(str: String?) = str?.toIntOrNull()?.let { true } ?: false

    fun setTextFields(str: String) {

        if (result.text.isNotEmpty() && !isInteger(str)) {
            math_operation.text = result.text
            result.text = ""
        }
        math_operation.append(str)
    }
    fun setTextResult(str: String) {
        try {
            val ex = ExpressionBuilder(str).build()
            val resultEval = ex.evaluate()
            val longRes = resultEval.toLong()
            if (resultEval == longRes.toDouble()) {
                result.text = longRes.toString()
            } else {
                result.text = resultEval.toString()
            }
        } catch (e: Exception) {
            Log.d("Error","${e.message}")
        }
    }
    fun clearTextFields() {
        math_operation.text = ""
        result.text = ""
    }
}
