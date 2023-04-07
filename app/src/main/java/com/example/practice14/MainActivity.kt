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

    private var questionIndex = 0;
    private var questionsArray = listOf(
        Question("Дед мороз существует ?", true),
        Question("Санта амбассадор Кока Колы ?", true),
        Question("Санта помещается в трубу ?", false),
        Question("Дед мороз живёт на Южном полюсе ?", false),

        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var questionText: TextView = findViewById(R.id.questions_text)

        questionText.setText(questionsArray[questionIndex].title)
        switchQuestion()

        val button_true: Button = findViewById(R.id.button_true)
        val button_falas: Button = findViewById(R.id.button_false)


        button_true.setOnClickListener{
            onAnswer(true, questionsArray[questionIndex].isTrue)
        }

        button_falas.setOnClickListener{
            onAnswer(false, questionsArray[questionIndex].isTrue)
        }
    }

    private fun switchQuestion() {
        val button_back: Button = findViewById(R.id.button_back)
        val button_next: Button = findViewById(R.id.button_next)
        var questionText: TextView = findViewById(R.id.questions_text)
        if (questionsArray.count() >= 1) {
            button_next.setOnClickListener {
                if (questionIndex >= questionsArray.count() - 1) questionIndex = 0
                questionIndex ++
                questionText.setText(questionsArray[questionIndex].title)

            }
            button_back.setOnClickListener {
                if (questionIndex <= 0) questionIndex = questionsArray.count() - 1
                questionIndex --
                questionText.setText(questionsArray[questionIndex].title)

            }
        }
    }
    private fun checkFinishState() {
        if (questionsArray.count() == 1) {
            val intent = Intent(this, FinishActivity::class.java)
            finish()
            return startActivity(intent)
        }
    }
    private fun onAnswer(answerType: Boolean, questionAnswer: Boolean) {

        var questionText: TextView = findViewById(R.id.questions_text);
        if (answerType == questionAnswer) {
            checkFinishState()
            questionsArray = questionsArray.filterIndexed { index, _ -> index != questionIndex }
            questionIndex = 0
            questionText.setText(questionsArray[questionIndex].title)
            Toast.makeText(this,R.string.correct_toast, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,R.string.wrong_toast, Toast.LENGTH_SHORT).show()

        }
    }
}