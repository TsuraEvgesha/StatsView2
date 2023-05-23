package ru.netology.statsview.ui

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.provider.SyncStateContract.Helpers.update
import android.util.AttributeSet
import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.content.withStyledAttributes
import ru.netology.statsview.R
import ru.netology.statsview.utils.AndroidUtils


import kotlin.random.Random.Default.nextInt

class StatsView @JvmOverloads constructor(
    context:Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int =0,
    defStyleRes: Int =0,
): View(
    context,
    attributeSet,
    defStyleAttr,
    defStyleRes
) {
    private var radius = 0F
    private var center = PointF(0F, 0F)
    private var oval = RectF(0F, 0F, 0F, 0F)

    private var textSize = AndroidUtils.dp(context, 40).toFloat()
    private var lineWidth = AndroidUtils.dp(context, 5)
    private var colors = emptyList<Int>()
    private var step = 0F

    private var progress = 0F
    private var valueAnimator: ValueAnimator? = null

    init {
        context.withStyledAttributes(attributeSet, R.styleable.StatsView) {
            textSize = getDimension(R.styleable.StatsView_textSize, textSize)
            lineWidth = getDimension(R.styleable.StatsView_lineWidth, lineWidth.toFloat()).toInt()
            val resId = getResourceId(R.styleable.StatsView_colors, 0)
            colors = resources.getIntArray(resId).toList()
        }
    }

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth =lineWidth.toFloat()
        style=Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
    }
    private var freePaint= Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth =lineWidth.toFloat()
        style=Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        color= Color.LTGRAY

    }
    private var textPaint= Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = this@StatsView.textSize
        style=Paint.Style.FILL
        textAlign = Paint.Align.CENTER
    }
    var data: List<Float> = emptyList()
    set(value) {
        field = value
        update()

    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius= kotlin.math.min(w, h) / 2F - lineWidth/ 2
        center = PointF(w / 2F,h / 2F)
        oval = RectF(
            center.x - radius, center.y - radius,
            center.x + radius, center.y + radius
        )

    }


    override fun onDraw(canvas: Canvas) {
        if (data.isEmpty()) {
            return
        }
//        canvas.drawCircle(center.x,center.y,radius, freePaint)

        var startAngle = -90F
        for ((index, datum) in data.withIndex()) {

            val angle = 360F * datum
            paint.color = colors.getOrNull(index) ?: generateRandomColor()
            canvas.drawArc(oval, startAngle + (progress * 360F), angle * progress, false, paint)
            startAngle += angle
            step = angle + startAngle

        }


//        второе задание
//        if (data.sum()>=1F){
//            val angle = 1F
//            paint.color = colors[4]
//            canvas.drawArc(oval, startAngle,angle,false, paint)
//        }

        canvas.drawText(

            "%.2f%%".format(data.sum() * 100),
            center.x,
            center.y + textPaint.textSize / 4,
            textPaint,
        )

    }

    private fun update() {
        valueAnimator?.let {
            it.removeAllListeners()
            it.cancel()
        }
        progress = 0F
        progress.toInt()

        valueAnimator = ValueAnimator.ofFloat(0F, 1F)
            .apply {
                addUpdateListener { anim ->
                    progress = anim.animatedValue as Float
                    invalidate()
                }
                duration = 1000
                interpolator = LinearInterpolator()
            }.also {
                it.start()

            }
    }


    private fun generateRandomColor() = nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())

}
