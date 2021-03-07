package com.badschizoids.ciphergame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.badschizoids.ciphergame.tools.Level
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry)
        val button: MaterialButton = findViewById(R.id.next)
        button.visibility = View.GONE
        val textView = findViewById<MaterialTextView>(R.id.text)
        textView.textSize = 28f
        val word = "Конкурирующая компания «Стела» отказала " +
                "корпорации «Индекс» в контракте по передаче " +
                "данных посередине сделки. Данные были переданы, " +
                "но на них наложен шифр.\n" +
                "Как ни странно, «Индекс» резко начал нанимать шифровальщиков, " +
                "не сильно заботясь о качестве, и вам удалось проскочить    " +
                "на стажировку."
        var counter = 0
        Log.e("data", Level.NO.name)
        MainScope().launch {
            while (counter < word.length) {
                delay(65)
                textView.setText(textView.text.toString() + word[counter])
                if (counter == word.length-1)
                    button.visibility = View.VISIBLE
                counter++
            }
        }
        button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}