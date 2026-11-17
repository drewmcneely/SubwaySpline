package com.example.subwayspline

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ScaleGestureDetector

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



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, imageToScreen, null)
    }
}
