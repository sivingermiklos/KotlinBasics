package asztali.mobil.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var openGreetingButton: Button
    private lateinit var openCalculatorButton : Button
    private lateinit var openWeatherButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    //   ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
    //      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
    //      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
    //      insets
    //   }
        openGreetingButton = findViewById(R.id.openGreetingButton);
        openCalculatorButton = findViewById(R.id.openCalculatorButton);
        openWeatherButton = findViewById(R.id.openWeatherButton);


        openGreetingButton.setOnClickListener {
            val intent = Intent(this, GreetingActivity::class.java)
            startActivity(intent)
        }
        openCalculatorButton.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
        openWeatherButton.setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }
    }

}