package com.example.subwayspline

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.view.View

class DiagramView(context: Context, private val bitmap: Bitmap) : View(context) {
    private val imageToScreen = Matrix()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, imageToScreen, null)
    }
}