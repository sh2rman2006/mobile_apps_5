package com.example.lab5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupColors)

        findViewById<Button>(R.id.btnApply).setOnClickListener {
            // Определяем, какой цвет выбран
            val colorString = when (radioGroup.checkedRadioButtonId) {
                R.id.rbRed -> "Красный"
                R.id.rbGreen -> "Зеленый"
                R.id.rbBlue -> "Синий"
                else -> "Белый"
            }

            // Упаковываем данные и отправляем обратно
            val resultIntent = Intent().apply {
                putExtra("SELECTED_COLOR", colorString)
            }

            setResult(RESULT_OK, resultIntent)
            finish() // Закрываем экран
        }
    }
}