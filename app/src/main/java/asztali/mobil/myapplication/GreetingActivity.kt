package asztali.mobil.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class GreetingActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var greetingButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var openCalculatorButton: Button

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(nameEditText.windowToken, 0)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_greeting)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        // UI elemek inicializálása
        nameEditText = findViewById(R.id.nameEditText);
        greetingButton = findViewById(R.id.greetingButton);
        resultTextView = findViewById(R.id.resultTextView);
        openCalculatorButton = findViewById(R.id.openCalculatorButton);
        greetingButton.setOnClickListener {
            val name = nameEditText.text.toString().trim();

            // Ellenőrizzük a mező kitöltöttségét
            if (name.isNotEmpty()) {
                val greetingText = when {
                    name.length <= 3 -> "Szia, $name! De rövid neved van!"
                    name.length >= 10 -> "Szia, $name! De hosszú neved van!"
                    else -> "Szia, $name!"
                }

                // Eredmény kiírása
                resultTextView.text = greetingText

                nameEditText.text.clear();
                // Segédfüggvény a billentyűzet elrejtéséhez
                hideKeyboard();
            } else {
                // Hibaüzenet megjelenítése
                Toast.makeText(this, "nemjo sracok, segitseg HEY GOOGLE CALL 911", Toast.LENGTH_SHORT).show();
            }
        }

        openCalculatorButton.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
    } //ONCREATE
}//CLASS