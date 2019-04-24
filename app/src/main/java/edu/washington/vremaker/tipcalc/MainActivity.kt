package edu.washington.vremaker.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import android.text.TextWatcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tip = findViewById<Button>(R.id.tip)
        val amount = findViewById<EditText>(R.id.amount)
        tip.setEnabled(false)
        amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                /*enables tip button when there's text in the editor */
                tip.setEnabled(true)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               /*Prefixes with a $, but does not use a mask because all the
                examples I found were java but I asked and Joy said that was fine?
                Also, The user can enter text before he $ but I can't find a fix for that
                I might go to office hours for that even though technically
                this will have been turned in */
                if (!s.toString().startsWith("$")) {
                   val display = "$" + amount.text
                    amount.setText(display)
                    amount.setSelection(amount.length())
                }
            }
        })
        /* when the tip button is clicked, sends a toast with tip amount */
        tip.setOnClickListener() {
            Toast.makeText(this@MainActivity, findTip(amount.text.toString()), Toast.LENGTH_SHORT).show()
        }
    }
    /* converts the tip from a string into another string that is 15 % of
     the original string int value and makes sure the format of tip  is $#.## */
    private fun findTip(amount: String ): String {
        val toMultiply = amount.substring(1).toDouble()
        var needsRound = toMultiply * .15
        var total = String.format("%.2f", needsRound)
        return "$" + total
    }

}

