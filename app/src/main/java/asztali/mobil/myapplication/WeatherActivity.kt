package asztali.mobil.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import asztali.mobil.myapplication.model.WeatherResponse
import asztali.mobil.myapplication.network.WeatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity : AppCompatActivity(){

    private lateinit var textviewTemp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weather)
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        //  val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        //v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        //insets
        //}

        textviewTemp = findViewById(R.id.textview_temp)

        fetchWeatherData()
    }//ONCREATE

    private fun fetchWeatherData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService = retrofit.create(WeatherService::class.java);

        // https://api.openweathermap.org/data/2.5/weather?q=Tatabánya&appid=f9a773d23d6bdc7207fd232c5d2a6dc2&units=metric
        val call = weatherService.getWeather("Tatabánya", "f9a773d23d6bdc7207fd232c5d2a6dc2", "metric");

        call.enqueue(object: Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("HIBA", "Hiba történt az API kérés során!", t)
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful){
                    val weatherResponse = response.body();
                    if (weatherResponse != null){
                        val temperature = weatherResponse.main.temp
                        val cityName = weatherResponse.name
                        textviewTemp.text = "Hőmérséklet (" + cityName + "): " + temperature.toString() + "°C"
                    }
                    Log.d("API", "Sikeres válasz: ${response.body()}")
                }
            }
        });
    }
}