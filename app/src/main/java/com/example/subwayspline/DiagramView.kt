package com.example.subwayspline

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ScaleGestureDetector
import android.graphics.PointF
import android.graphics.Paint
import android.graphics.Color

class DiagramView(context: Context, private val bitmap: Bitmap) : View(context) {
    private val imageToScreen = Matrix()

    private val panDetector = GestureDetector(context,
        object : GestureDetector.SimpleOnGestureListener() {
            override fun onScroll(e1: MotionEvent?, e2: MotionEvent,
                                  distanceX: Float, distanceY: Float): Boolean {
                imageToScreen.postTranslate(-distanceX, -distanceY)
                invalidate()
                return true
            }
        })

    private val zoomDetector = ScaleGestureDetector(context,
        object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                val s = detector.scaleFactor
                imageToScreen.postScale(s, s, detector.focusX, detector.focusY)
                invalidate()
                return true
            }
        })

    override fun onTouchEvent(event: MotionEvent): Boolean {
        panDetector.onTouchEvent(event)
        zoomDetector.onTouchEvent(event)
        return true
    }

    var dot: PointF? = null
        set(value) { field = value; invalidate() }
    private val dotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.rgb(30, 120, 255) }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, imageToScreen, null)
        dot?.let {
            val p = floatArrayOf(it.x, it.y)
            imageToScreen.mapPoints(p)          // image px → screen px, i.e. M(p)
            canvas.drawCircle(p[0], p[1], 20f, dotPaint)
        }
    }


}
