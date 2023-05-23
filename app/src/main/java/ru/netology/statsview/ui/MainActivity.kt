package ru.netology.statsview.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.TextView
import ru.netology.statsview.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = findViewById<StatsView>(R.id.statsView)
        view.postDelayed({
            view.data = listOf(
                0.25F,
                0.25F,
                0.25F,
                0.25F,
            )
        }, 3000)
//        val textView = findViewById<TextView>(R.id.textView)
//        view.startAnimation(
//            AnimationUtils.loadAnimation(this, R.anim.animation).apply {
//                setAnimationListener(object : Animation.AnimationListener {
//                    override fun onAnimationStart(animation: Animation?) {
//                        textView.text = "onAnimationStart"
//
//                    }
//
//                    override fun onAnimationEnd(animation: Animation?) {
//                        textView.text = "onAnimationEnd"
//
//                    }
//
//                    override fun onAnimationRepeat(animation: Animation?) {
//                        textView.text = "onAnimationRepeat"
//
//                    }
//
//                })
//            }
//        )
//        для одиночной анимации
//        ObjectAnimator.ofFloat(view, View.ALPHA,0.25F,1F).apply {
//            startDelay = 500
//            duration = 300
//            interpolator = BounceInterpolator()
//        }.start()
//для анимации 1 обьектом нескольких свойств но неудобно
//        val rotation = PropertyValuesHolder.ofFloat(View.ROTATION,0F,360F)
//        val alpha =PropertyValuesHolder.ofFloat(View.ALPHA,0F,1F)
//        ObjectAnimator.ofPropertyValuesHolder(view,rotation,alpha)
//            .apply {
//                startDelay = 500
//                duration =500
//                interpolator = LinearInterpolator()
//            }.start()
//        view.animate()
//            .rotation(360F)
//            .scaleX(1.2F)
//            .scaleY(1.2F)
//            .setInterpolator(LinearInterpolator())
//            .setStartDelay(500)
//            .setDuration(500)
//            .start()
//        AnimatorSet для построения нескольких анимаций в определенном порядке автоматически
//        val alpha = ObjectAnimator.ofFloat(view,View.ALPHA,0.25F,1F).apply {
//            duration = 300
//            interpolator =LinearInterpolator()
//        }
//        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X,0F,1F)
//        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y,0F,1F)
//        val scale = ObjectAnimator.ofPropertyValuesHolder(view,scaleX,scaleY).apply {
//            duration =300
//            interpolator = BounceInterpolator()
//        }
//        AnimatorSet().apply {
//            startDelay = 500
//            playSequentially(scale,alpha)
//        }.start()

//        builder ->>>> play(...) .after(...) .before(...) .with(...)
    }
}
