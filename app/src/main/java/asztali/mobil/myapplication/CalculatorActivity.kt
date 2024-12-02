package asztali.mobil.myapplication

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class CalculatorActivity : AppCompatActivity() {

    private lateinit var firstNumberEditText: EditText
    private lateinit var secondNumberEditText: EditText
    private lateinit var additionButton: Button
    private lateinit var clearButton: Button
    private lateinit var resultTextView: TextView

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(firstNumberEditText.windowToken, 0)
        imm.hideSoftInputFromWindow(secondNumberEditText.windowToken, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//           val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//           v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//           insets
//       }

        firstNumberEditText = findViewById(R.id.firstNumberEditText)
        secondNumberEditText = findViewById(R.id.secondNumberEditText)
        additionButton = findViewById(R.id.additionButton)
        clearButton = findViewById(R.id.clearButton)
        resultTextView = findViewById(R.id.resultTextView)

        additionButton.setOnClickListener {
            performAddition()
            hideKeyboard();
        }

        clearButton.setOnClickListener {
            clearFields()
        }

    }
    private fun performAddition() {
        // Get the text from the EditText fields and try to parse them into integers
        val firstNumberText = firstNumberEditText.text.toString()
        val secondNumberText = secondNumberEditText.text.toString()

        // Check if both inputs are valid numbers
        if (firstNumberText.isNotEmpty() && secondNumberText.isNotEmpty()) {
            try {
                val firstNumber = Integer.parseInt(firstNumberText)
                val secondNumber = Integer.parseInt(secondNumberText)

                // Perform addition and show the result
                val result = firstNumber + secondNumber
                resultTextView.text = result.toString()
            } catch (e: NumberFormatException) {
                resultTextView.text = "Érvénytelen bemenet"
            }
        } else {
            resultTextView.text = "Add meg mindkettő számot!"
        }
    }

    // Function to clear the input fields and the result
    private fun clearFields() {
        firstNumberEditText.text.clear()
        secondNumberEditText.text.clear()
        resultTextView.text = ""
    }
}