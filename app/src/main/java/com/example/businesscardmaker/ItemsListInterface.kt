package com.example.businesscardmaker

import android.widget.ImageView
import com.example.businesscardmaker.widget_utils.DragableView

interface ItemsListInterface {
    fun onClick(model:DragableView, img:ImageView)
}