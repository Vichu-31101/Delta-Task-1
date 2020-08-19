package com.example.deltatask_1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.Fragment
import com.example.deltatask_1.Frag1
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fragment1 = supportFragmentManager.findFragmentById(R.id.draw) as Frag1
        fragment1.canvas.setOnTouchListener(View.OnTouchListener { _, _ ->
            var fragment = supportFragmentManager.findFragmentById(R.id.replicate) as Frag2
            fragment.runCode()
            return@OnTouchListener false
        })

    }


}