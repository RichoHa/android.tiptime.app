package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat //Required for the Ceil function (Round up)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Begin the calculation once the button is pressed.
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip() {
        //Take editable text [not string, but actually editable], require converting to string
        val stringInTextField = binding.costOfService.text.toString()
        //Change the string to a double
        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null) {
            binding.tipResult.text = ""
            return
        }


        //based on the ID chosen, different double value is saved to tipPercentage
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //Calculating the tip value
        var tip = tipPercentage * cost

        //Determining if the switch is checked/true
        //if switch is clicked, change the tip value
        if (binding.roundUpTip.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        //To get currency value format- remember to pick java Text.
        NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        //updating the tip results
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

}
