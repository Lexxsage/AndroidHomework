package com.example.homework1

import android.graphics.Color
import android.os.Bundle
import android.os.PatternMatcher
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    val studentMMap = mutableMapOf<Int, Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.editTextTextStudent)
        val showButton: Button = findViewById(R.id.ShowButton)
        val textView: TextView = findViewById(R.id.textView)

        //enter key processing
        editText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN){
                val id = System.currentTimeMillis().toInt()
                val studentString = editText.text.toString()

                if (studentString.split(" ").size  != 4) {
                    editText.setError("Error: student info format")
                    return@setOnKeyListener true
                }

                val patFIO = Pattern.compile("\\b[A-Z.А-Я]{1}[a-z.а-я]+\\b")
                val matchName = patFIO.matcher(studentString.split(" ")[0])
                if(!matchName.find()){
                    editText.setError("Error: student name format")
                    return@setOnKeyListener true
                }

                val matchSurname = patFIO.matcher(studentString.split(" ")[1])
                if(!matchSurname.find()){
                    editText.setError("Error: student surname format")
                    return@setOnKeyListener true
                }


                val patGrade = Pattern.compile("\\b\\d+[A-Z.А-Я]{1}+\\b")
                val matchGrade = patGrade.matcher(studentString.split(" ")[2])
                if (!matchGrade.find()) {
                    editText.setError("Error: student grade format")
                    return@setOnKeyListener true
                }

                //val patBirthDate = Pattern.compile("(?:19|20)\\d\\d$")
                val patBirthDate = Pattern.compile("(20)(?:0|1)(?:0|3)$")
                val matchBirthDate = patBirthDate.matcher(studentString.split(" ")[3])
                if (!matchBirthDate.find()) {
                    editText.setError("Error: student birth year format")
                    return@setOnKeyListener true
                }

                val student = Student(id, studentString.split(" ")[0], studentString.split(" ")[1], studentString.split(" ")[2], studentString.split(" ")[3])
                editText.text.clear()
                studentMMap.put(id, student)

                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        showButton.setOnClickListener {
            textView.text = studentMMap.values.joinToString(separator = "\n")
        }

    }

}