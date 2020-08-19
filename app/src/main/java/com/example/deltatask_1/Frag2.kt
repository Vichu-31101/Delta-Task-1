package com.example.deltatask_1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.fragment_frag1.view.*
import kotlinx.android.synthetic.main.fragment_frag2.view.*


class Frag2 : Fragment() {

    lateinit var canvas:ReplicateView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        canvas = context?.let { ReplicateView(it) }!!
        val params = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val rootView: View =
            inflater.inflate(R.layout.fragment_frag2, container, false)

        rootView.root2.addView(canvas,params)
        return rootView
    }

    fun runCode(){
        canvas.draw()
    }
}