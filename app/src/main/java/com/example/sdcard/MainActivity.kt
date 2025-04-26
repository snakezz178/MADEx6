package com.example.sdcard

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var rollnoEditText: EditText
    private lateinit var cgpaEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button

    private val fileName = "student_data.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.editTextText)
        rollnoEditText = findViewById(R.id.editTextText2)
        cgpaEditText = findViewById(R.id.editTextText3)
        saveButton = findViewById(R.id.button)
        loadButton = findViewById(R.id.button2)

        saveButton.setOnClickListener {
            saveData()
        }

        loadButton.setOnClickListener {
            loadData()
        }
    }

    private fun saveData() {
        val name = nameEditText.text.toString()
        val rollNo = rollnoEditText.text.toString()
        val cgpa = cgpaEditText.text.toString()

        if (name.isBlank() || rollNo.isBlank() || cgpa.isBlank()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val data = "$name\n$rollNo\n$cgpa"

        try {
            val file = File(filesDir, fileName)
            file.writeText(data)
            Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show()

            // 👉 Clear the EditTexts after saving
            nameEditText.text.clear()
            rollnoEditText.text.clear()
            cgpaEditText.text.clear()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to Save Data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadData() {
        try {
            val file = File(filesDir, fileName)
            if (!file.exists()) {
                Toast.makeText(this, "No saved data found", Toast.LENGTH_SHORT).show()
                return
            }

            val lines = file.readLines()

            if (lines.size >= 3) {
                nameEditText.setText(lines[0])
                rollnoEditText.setText(lines[1])
                cgpaEditText.setText(lines[2])
                Toast.makeText(this, "Data Loaded Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Saved data is incomplete", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Failed to Load Data", Toast.LENGTH_SHORT).show()
        }
    }
}
