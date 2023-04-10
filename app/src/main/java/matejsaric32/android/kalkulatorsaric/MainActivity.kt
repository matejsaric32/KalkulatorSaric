package matejsaric32.android.kalkulatorsaric

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import matejsaric32.android.kalkulatorsaric.databinding.ActivityMainBinding
import matejsaric32.android.kalkulatorsaric.databinding.DialogProgressBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private var calcStringBuilder : StringBuilder = StringBuilder()

    private var isDecimalPlaced = true
    private lateinit var mProgressDialog: Dialog

    companion object{
        const private val TAG = "TAG_Calc"
        const private val TAG1 = "TAG_Calc1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.btn0?.setOnClickListener {
            calcStringBuilder.append("0")
            binding?.tvCalculate?.text = calcStringBuilder.toString()
        }
        binding?.btn1?.setOnClickListener {
            calcStringBuilder.append("1")
            binding?.tvCalculate?.text = calcStringBuilder.toString()
        }
        binding?.btn2?.setOnClickListener {
            calcStringBuilder.append("2")
            binding?.tvCalculate?.text = calcStringBuilder.toString()
        }
        binding?.btn3?.setOnClickListener {
            calcStringBuilder.append("3")
            binding?.tvCalculate?.text = calcStringBuilder.toString()
        }
        binding?.btn4?.setOnClickListener {
            calcStringBuilder.append("4")
            binding?.tvCalculate?.text = calcStringBuilder.toString()
        }
        binding?.btn5?.setOnClickListener {
            calcStringBuilder.append("5")
            binding?.tvCalculate?.text = calcStringBuilder.toString()
        }
        binding?.btn6?.setOnClickListener {
            calcStringBuilder.append("6")
            binding?.tvCalculate?.text = calcStringBuilder.toString()
        }
        binding?.btn7?.setOnClickListener {
            calcStringBuilder.append("7")
            binding?.tvCalculate?.text = calcStringBuilder.toString()
        }
        binding?.btn8?.setOnClickListener {
            calcStringBuilder.append("8")
            binding?.tvCalculate?.text = calcStringBuilder.toString()
        }
        binding?.btn9?.setOnClickListener {
            calcStringBuilder.append("9")
            binding?.tvCalculate?.text = calcStringBuilder.toString()
        }

        binding?.btnBackSpace?.setOnClickListener {
            if (calcStringBuilder.isNotEmpty()) {
                calcStringBuilder.deleteCharAt(calcStringBuilder.length - 1)
                binding?.tvCalculate?.text = calcStringBuilder.toString()
            }
        }

        binding?.btnPlus?.setOnClickListener {
            if (calcStringBuilder.toString().isNotEmpty()){
                if (calcStringBuilder[calcStringBuilder.length - 1].isDigit()){
                    calcStringBuilder.append(" + ")
                    binding?.tvCalculate?.text = calcStringBuilder.toString()
                    isDecimalPlaced = true
                }
            }
        }

        binding?.btnMinus?.setOnClickListener {
            if (calcStringBuilder.toString().isNotEmpty()){
                if (calcStringBuilder[calcStringBuilder.length - 1].isDigit()){
                    calcStringBuilder.append(" - ")
                    binding?.tvCalculate?.text = calcStringBuilder.toString()
                    isDecimalPlaced = true
                }
            }
        }

        binding?.btnMultplay?.setOnClickListener {
            if (calcStringBuilder.toString().isNotEmpty()){
                if (calcStringBuilder[calcStringBuilder.length - 1].isDigit()){
                    calcStringBuilder.append(" * ")
                    binding?.tvCalculate?.text = calcStringBuilder.toString()
                    isDecimalPlaced = true
                }
            }
        }

        binding?.btnDivision?.setOnClickListener {
            if (calcStringBuilder.toString().isNotEmpty()){
                if (calcStringBuilder[calcStringBuilder.length - 1].isDigit()){
                    calcStringBuilder.append(" / ")
                    binding?.tvCalculate?.text = calcStringBuilder.toString()
                    isDecimalPlaced = true
                }
            }
        }

        binding?.btnPoint?.setOnClickListener{
            if (calcStringBuilder.toString().isNotEmpty()){
                if (calcStringBuilder[calcStringBuilder.length - 1].isDigit() && isDecimalPlaced){
                    calcStringBuilder.append(".")
                    binding?.tvCalculate?.text = calcStringBuilder.toString()
                    isDecimalPlaced = false
                }
            }
        }

        binding?.btnEquals?.setOnClickListener {
            if (calcStringBuilder.toString().isNotEmpty()) {
                if (calcStringBuilder[calcStringBuilder.length - 1].isDigit()) {
                    calculateRes()
                } else {
                    Toast.makeText(this, "No good!!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding?.btnC?.setOnClickListener{
            changeColorOfButtons()
        }
    }

    private fun changeColorOfButtons() {
        binding?.btnC?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btnPoint?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btnBackSpace?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btnDivision?.setBackgroundColor(resources.getColor(R.color.teal_200))

        binding?.btn7?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btn8?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btn9?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btnMultplay?.setBackgroundColor(resources.getColor(R.color.teal_200))

        binding?.btn4?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btn5?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btn6?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btnMinus?.setBackgroundColor(resources.getColor(R.color.teal_200))

        binding?.btn1?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btn2?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btn3?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btnPlus?.setBackgroundColor(resources.getColor(R.color.teal_200))

        binding?.btn0?.setBackgroundColor(resources.getColor(R.color.teal_200))
        binding?.btnEquals?.setBackgroundColor(resources.getColor(R.color.teal_200))
    }


    private fun calculateRes() {
        var stringyList = calcStringBuilder.toString().split(" ")
        var processedInput = ArrayDeque<String>(stringyList)

        do{
            when(processedInput[1]){
                "+" -> {
                    processedInput[2] = (processedInput[2].toDouble() + processedInput[0].toDouble()).toString()
                    processedInput.removeFirst()
                    processedInput.removeFirst()
                }
                "-" -> {
                    processedInput[2] = (processedInput[0].toDouble() - processedInput[2].toDouble()).toString()
                    processedInput.removeFirst()
                    processedInput.removeFirst()
                }
                "/" -> {
                    processedInput[2] = (processedInput[0].toDouble() / processedInput[2].toDouble()).toString()
                    processedInput.removeFirst()
                    processedInput.removeFirst()
                }
                "*" -> {
                    processedInput[2] = (processedInput[2].toDouble() * processedInput[0].toDouble()).toString()
                    processedInput.removeFirst()
                    processedInput.removeFirst()
                }
            }

        }while(processedInput.size >= 2)

        showProgressDialog(resources.getString(R.string.language));

        Handler(Looper.getMainLooper()).postDelayed({
            hideProgressDialog()
            binding?.tvResault?.text = processedInput[0]
            Log.d(TAG, "= ${processedInput[0]}")
            processedInput.clear()
        }, 2000)



    }

    private fun showProgressDialog(text: String) {
        mProgressDialog = Dialog(this)
        val binding = DialogProgressBinding.inflate(layoutInflater)
        mProgressDialog!!.setContentView(binding.root)
        binding.tvProgressText.text = text
        mProgressDialog.show()
    }

    fun hideProgressDialog() {
        mProgressDialog.dismiss()
    }
}

