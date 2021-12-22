package com.business.card.visiting.cardmaker

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.business.card.visiting.cardmaker.widget_utils.DragableView
import kotlinx.android.synthetic.main.items_list_item.view.*

class ItemsListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {


    @RequiresApi(Build.VERSION_CODES.O)
    fun bindItems(cntx:Context,model: DragableView) {
        itemView.menu_item_text.text = model.tooltipText
        if (model.getChildAt(0).transitionName?.toString()=="Gone"){
            itemView.widget_visible.setImageDrawable(cntx.getDrawable(R.drawable.menu_invisble))
        }


        when (model.tooltipText){
            "Logo" ->{
                itemView.menu_item_logo.setImageDrawable(cntx.getDrawable(R.drawable.menu_logo))
            }
            "Address" ->{
                itemView.menu_item_logo.setImageDrawable(cntx.getDrawable(R.drawable.menu_addr))

            }
            "Website" ->{
                itemView.menu_item_logo.setImageDrawable(cntx.getDrawable(R.drawable.menu_web))

            }
            "Email" ->{
                itemView.menu_item_logo.setImageDrawable(cntx.getDrawable(R.drawable.menu_mail))

            }
            "Phone No" ->{
                itemView.menu_item_logo.setImageDrawable(cntx.getDrawable(R.drawable.menu_phone))
            }
            "Designation" ->{
                itemView.menu_item_logo.setImageDrawable(cntx.getDrawable(R.drawable.menu_designation))

            }
            "Name" ->{
                itemView.menu_item_logo.setImageDrawable(cntx.getDrawable(R.drawable.menu_name))

            }
            "Company Name" ->{
                itemView.menu_item_logo.setImageDrawable(cntx.getDrawable(R.drawable.menu_cmpny))

            }
            else ->{

            }
        }


    }
}