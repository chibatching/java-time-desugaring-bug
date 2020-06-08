package com.example.desugerbug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeParseException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val parsed = try {
            LocalDate.parse("")
        } catch (e: DateTimeParseException) {
            Log.e("MainActivity", "Parse error", e)
            null
        }
        Log.d("MainActivity", "$parsed")
    }
}
