package com.example.lab5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE_COLOR = 100
    }

    private lateinit var mainLayout: LinearLayout
    private lateinit var tvStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainLayout = findViewById(R.id.mainLayout)
        tvStatus = findViewById(R.id.tvStatus)

        findViewById<Button>(R.id.btnOpenSettings).setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            @Suppress("DEPRECATION") // Глушим варнинг, т.к. метод нужен по методичке
            startActivityForResult(intent, REQUEST_CODE_COLOR)
        }

        findViewById<Button>(R.id.btnOpenAbout).setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }

    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_COLOR && resultCode == RESULT_OK) {
            val selectedColor = data?.getStringExtra("SELECTED_COLOR") ?: "white"

            tvStatus.text = getString(R.string.color_status_format, selectedColor)

            val colorResId = when (selectedColor) {
                "Красный" -> R.color.bg_red
                "Зеленый" -> R.color.bg_green
                "Синий" -> R.color.bg_blue
                else -> R.color.bg_default
            }

            mainLayout.setBackgroundColor(ContextCompat.getColor(this, colorResId))
        }
    }
}