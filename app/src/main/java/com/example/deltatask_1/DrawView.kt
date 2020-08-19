package com.example.deltatask_1

import android.app.Activity
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

class MyCanvasView(context: Context) : View(context) {



    private val curPath = Path()

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



    private var currentX = 0f
    private var currentY = 0f

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f



    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        curBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        curCanvas = Canvas(curBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        // Draw the drawing so far
        canvas.drawBitmap(curBitmap, 0f, 0f, paint)
        // Draw any current squiggle
        canvas.drawPath(curPath, paint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }


    private fun touchStart() {

        curPath.reset()
        curPath.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
    }

    private fun touchMove() {
        curPath.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
        currentX = motionTouchEventX
        currentY = motionTouchEventY
        path = curPath

        invalidate()
    }

    private fun touchUp() {
        // Reset the path so it doesn't get drawn again.
        curCanvas.drawPath(curPath,paint)
        curPath.reset()
    }

}