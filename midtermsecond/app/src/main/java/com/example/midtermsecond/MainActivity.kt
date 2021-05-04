package com.example.midtermsecond

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.abs
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {


        cardNumber.addTextChangedListener {
            if (cardNumber.text.isNotEmpty()) {
                textView2.setText(cardNumber.text.toString())
                if (cardNumber.text.length == 1 && cardNumber.text.toString() == 5.toString()) {
                    imageView.setImageResource(R.drawable.detail)
                } else if (cardNumber.text.length == 1 && cardNumber.text.toString() == 4.toString()) {
                    imageView.setImageResource(R.drawable.visa)
                }
                var res = ""
                for (i in cardNumber.text.indices) {
                    if (i % 4 == 0)
                        res += " " + cardNumber.text[i]
                    else
                        res += cardNumber.text[i]
                }
                textView2.setText(res)
            }

        }

        date.addTextChangedListener {
            var currentMonth = Calendar.getInstance().get(Calendar.MONTH + 1).toString()
            var currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()
            var cardYear = date.text.takeLast(2).toString()
            if (date.text.isNotEmpty()) {
                expDate.setText(date.text.toString())
            }
            if (cardYear < currentYear) {
                Toast.makeText(this, "date expired", Toast.LENGTH_LONG).show()
                button.isEnabled = false
                button.isClickable = false
            } else {
                button.isEnabled = true
                button.isClickable = true
            }
            var d = ""
            for (i in date.text.indices) {
                if (i === 2) {
                    if (d > "12") {
                        Toast.makeText(this, "wrong month format", Toast.LENGTH_LONG).show()
                    }
                    if (d < currentMonth && cardYear == currentYear) {
                        Toast.makeText(this, "date expired pirv", Toast.LENGTH_LONG).show()
                        button.isEnabled = false
                        button.isClickable = false
                    }

                    d += "/" + date.text[i]
                } else {
                    d += date.text[i]
                }
                expDate.setText(d)
            }
        }


        name.addTextChangedListener {
            if (name.text.isNotEmpty()) {
                nameSurname.setText(name.text.toString())
            }
        }


        button.setOnClickListener {
            if (cardNumber.text.isEmpty() || name.text.isEmpty() || date.text.isEmpty() || cvv.text.isEmpty()) {
                Toast.makeText(this, "you should fill every field", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
                cardNumber.text.clear()
                name.text.clear()
                date.text.clear()
                cvv.text.clear()
            }
        }


    }
}