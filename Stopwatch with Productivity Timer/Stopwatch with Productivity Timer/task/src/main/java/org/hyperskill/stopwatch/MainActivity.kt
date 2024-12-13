package org.hyperskill.stopwatch

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvTimer: TextView
    private lateinit var btnStart: Button
    private lateinit var btnReset: Button
    private var isRunning = false
    private var secondsElapsed = 0
    private val handler = Handler(Looper.getMainLooper())

    private val timerRunnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                updateTimerDisplay()
                handler.postDelayed(this, 1000)
                secondsElapsed++
            }
        }
    }

    private fun startTimer() {
        if (!isRunning) {
            isRunning = true
            handler.post(timerRunnable)
        }
    }

    private fun stopTimer() {
        isRunning = false
        secondsElapsed = 0
        handler.removeCallbacks(timerRunnable)
    }

    private fun updateTimerDisplay() {
        val minutes = secondsElapsed / 60
        val seconds = secondsElapsed % 60
        val timeString = String.format("%02d:%02d", minutes, seconds)
        tvTimer.text = timeString
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializamos las vistas
        tvTimer = findViewById(R.id.textView)
        btnStart = findViewById(R.id.startButton)
        btnReset = findViewById(R.id.resetButton)

        // Configuramos el botón de inicio
        btnStart.setOnClickListener {
            startTimer()
            updateTimerDisplay()
        }


        // Configuramos el botón de reinicio
        btnReset.setOnClickListener {
            stopTimer()
            updateTimerDisplay()
        }


    }

}