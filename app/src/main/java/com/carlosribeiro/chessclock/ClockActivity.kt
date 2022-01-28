package com.carlosribeiro.chessclock

import android.content.Context
import android.os.*
import android.os.Vibrator
import android.os.VibrationEffect.EFFECT_CLICK
import android.os.VibrationEffect.createPredefined
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ClockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)

        var time = intent.getStringExtra("time")

        val button1: ImageButton = findViewById(R.id.button1)
        val button2: ImageButton = findViewById(R.id.button2)
        val timer1: TextView = findViewById(R.id.timer1)
        val timer2: TextView = findViewById(R.id.timer2)
        timer1.setText(time + ":00")
        timer2.setText(time + ":00")

        var whiteTurn= true
        var timeWhiteMs = time!!.toLong()
        timeWhiteMs *= 60000

        var timeBlackMs = time!!.toLong()
        timeBlackMs *= 60000
        "mm':'ss"


        class CountWhite : CountDownTimer(timeWhiteMs, 1000) {

            override fun onTick(millisUntilFinishedWhite: Long) {
                if(!whiteTurn){cancel()}else {
                    val min1 = (millisUntilFinishedWhite/60000)
                    val min1formatted = String.format("%d", min1)
                    val sec1 = ((millisUntilFinishedWhite%60000)/1000)
                    val sec1formatted = String.format("%02d", sec1)
                    timer1.setText("" + min1formatted + ":" + sec1formatted )
                    timeWhiteMs = millisUntilFinishedWhite
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        VibrationEffect.createPredefined(EFFECT_CLICK)
                    }
                }
            }
            override fun onFinish() {
                timer1.setText("Time's up!")
            }
        }

        class CountBlack : CountDownTimer(timeBlackMs, 1000) {
            

            override fun onTick(millisUntilFinishedBlack: Long) {
                if(whiteTurn==true){cancel()}else {
                    val min2 = (millisUntilFinishedBlack/60000)
                    val min2formatted = String.format("%d", min2)
                    val sec2 = ((millisUntilFinishedBlack%60000)/1000)
                    val sec2formatted = String.format("%02d", sec2)
                    timer2.setText("" + min2formatted + ":" + sec2formatted )
                    timeBlackMs = millisUntilFinishedBlack

                   }

            }


            override fun onFinish() {
                timer1.setText("Time's up!")
            }
        }

        fun Context.vibrate(milliseconds:Long = 500) {
            val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            val canVibrate = vibrator.hasVibrator()
            if(canVibrate){
                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.Q){
                    vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
                }else{
                    vibrator.vibrate(milliseconds)
                }
            }
        }

        button1.setOnClickListener {
            whiteTurn = false
            vibrate()
            CountBlack().start()
        }

        button2.setOnClickListener {
            whiteTurn=true
            vibrate()
            CountWhite().start()
        }


    }
}


