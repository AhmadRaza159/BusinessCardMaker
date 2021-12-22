package com.business.card.visiting.cardmaker.widget_utils

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi

class DragableView(
    context: Context,
    attrs: AttributeSet?
) : FrameLayout(context, attrs) {
    private var xx: Float = 0f
    private var yy = 0f
    private var dx = 0f
    private var dy = 0f
    var viewInterface: ViewInterface? = null

    ///////////////
    override fun hasOnClickListeners(): Boolean {
        return true
    }

    override fun setOnClickListener(l: OnClickListener?) {
        this.setBackgroundColor(Color.RED)
    }
    ///////////////

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var parentView = this.parent as View
        if (this.getChildAt(0).accessibilityClassName == "android.widget.TextView") {
            viewInterface?.setText(this.getChildAt(0) as TextView)
        } else if (this.getChildAt(0).accessibilityClassName == "android.widget.ImageView") {
            viewInterface?.setImage(this.getChildAt(0) as ImageView)
        }

        if (event?.action == MotionEvent.ACTION_DOWN) {
            xx = event.x
            yy = event.y
        }
        if (event?.action == MotionEvent.ACTION_MOVE) {

            dx = event.x - xx
            dy = event.y - yy
            if (this.x+dx<=parentView.width-this.width && this.y<=parentView.height-this.height && this.x>=0 && this.y>=0 ){

                this.x = this.x + dx
                this.y = this.y + dy

            }
            if (this.x<0){
                this.x=0F
            }
            if (this.y<0){
                this.y=0F
            }
            if (this.x+dx>parentView.width-width){
                this.x=parentView.width-width.toFloat()
            }
            if (this.y>parentView.height-this.height){

                this.y=parentView.height-this.height.toFloat()
            }
            xx = event.x
            yy = event.y
        }
        return true
    }

    interface ViewInterface {
        fun setText(view: TextView?)
        fun setImage(view: ImageView?)
    }
}