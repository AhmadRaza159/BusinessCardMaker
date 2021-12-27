package com.business.card.visiting.cardmaker.my_creation

import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_downloaded.view.*
import java.util.*


class MyCreationViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bindItems(text:String) {

        var arrayOfName=text.split("/")
        var filterNameArray=arrayOfName[arrayOfName.size-1].split("_")
        var actualName="${filterNameArray[0]} - ${filterNameArray[2]}"
        actualName=actualName.uppercase(Locale.getDefault())
        actualName=actualName.replace(".JPEG","")
        actualName=actualName.replace(".PDF","")

        itemView.item_downloaded_title.text = actualName

    }
}