package com.example.deltatask_1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.deltatask_1.Global.Companion.path

private const val STROKE_WIDTH = 12f

class ReplicateView(context: Context) : View(context) {



    private var curPath = Path()


    lateinit var curCanvas: Canvas
    lateinit var curBitmap: Bitmap

    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorAccent, null)


    // Set up the paint with which to draw.
    private val paint = Paint().apply {
        color = drawColor
        // Smooths out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = STROKE_WIDTH // default: Hairline-width (really thin)
    }



    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        curBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        curCanvas = Canvas(curBitmap)

        draw()
    }

    override fun onDraw(canvas: Canvas) {
        // Draw the drawing so far
        //canvas.drawBitmap(savedBitmap, 0f, 0f, paint)
        canvas.drawPath(curPath, paint)
        canvas.drawBitmap(curBitmap, 0f, 0f, paint)
    }




    fun draw(){
        curPath = path
        curCanvas.drawPath(curPath,paint)
        invalidate()
    }

}