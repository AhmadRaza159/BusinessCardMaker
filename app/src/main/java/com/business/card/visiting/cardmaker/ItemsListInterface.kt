package com.business.card.visiting.cardmaker

import android.widget.ImageView
import com.business.card.visiting.cardmaker.widget_utils.DragableView

interface ItemsListInterface {
    fun onClick(model:DragableView, img:ImageView)
}