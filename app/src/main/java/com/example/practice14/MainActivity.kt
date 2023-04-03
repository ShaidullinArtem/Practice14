    package com.example.practice14

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

    class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val questionsArray = arrayOfNulls<String>(2)
        questionsArray[0] = "How many days in week ?"
        questionsArray[1] = "How old are you ?"
        var questionText: TextView = findViewById(R.id.questions_text)

        var increment = 0;

        questionText.setText(questionsArray[increment])
        val button_true: Button = findViewById(R.id.button_true)
        val button_falas: Button = findViewById(R.id.button_false)

        button_true.setOnClickListener{
            if (increment == questionsArray.count()-1) {
                val intent = Intent(this, FinishActivity::class.java)
                return@setOnClickListener startActivity(intent)
            }

            increment++
            questionText.setText(questionsArray[increment])
            Toast.makeText(this,R.string.correct_toast, Toast.LENGTH_SHORT).show()
        }

        button_falas.setOnClickListener{
            val toast = Toast.makeText(this, R.string.wrong_toast, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }
    }
}