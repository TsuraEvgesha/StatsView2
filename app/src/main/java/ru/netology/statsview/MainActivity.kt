package ru.netology.statsview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.TextView
import ru.netology.statsview.ui.StatsView

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = findViewById<StatsView>(R.id.statsView)
        view.data = listOf(
            500F,
            500F,
            500F,
        )
        view.animate()
            .rotation(360F)
            .scaleX(1.2F)
            .scaleY(1.2F)
            .setInterpolator(LinearInterpolator())

        val textView = findViewById<TextView>(R.id.textView)
        view.startAnimation(
            AnimationUtils.loadAnimation(this, R.anim.animation).apply {
                setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        textView.text = "onAnimationStart"

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        textView.text = "onAnimationEnd"

                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                        textView.text = "onAnimationRepeat"

                    }

                })
            }
        )
    }
}