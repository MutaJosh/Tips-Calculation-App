package com.example.tippy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private const val INITIAL_PROGRES_AMOUNT = 15

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarTip.progress = INITIAL_PROGRES_AMOUNT

        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var msg = "Seekbar Changed to $progress"
                Log.i(TAG, msg)

                seekBarTipView.text = "$progress%"

//                Changes calculations when the seekbar is changed
                computerTipAndTotal()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        tvBaseAmount.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val msg = "afterTextChanged $s"
                Log.i(TAG, msg)

//                Change the calculations when the text is changed
                computerTipAndTotal()

            }

        })


    }

    private fun computerTipAndTotal() {

       if (tvBaseAmount.text.toString().isEmpty()){
           tvTipAmount.text = "No data available ..."
           tvTotal.text = "No data available ..."
           return
       }else{
           val baseAmount = tvBaseAmount.text.toString().toDouble()
           val tipPercentage = seekBarTip.progress

           val tipAmount = baseAmount * tipPercentage / 100
           val totalAmount = baseAmount + tipAmount

//                Displaying them on the screen
           tvTipAmount.text = "$ " + "%.2f".format(tipAmount)
           tvTotal.text = "$ " + "%.2f".format(totalAmount)
       }
    }
}