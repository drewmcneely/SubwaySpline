package com.example.subwayspline

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.BitmapFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bitmap = assets.open("subway-diagram.png").use { BitmapFactory.decodeStream(it) }
        setContentView(DiagramView(this, bitmap))
    }
}