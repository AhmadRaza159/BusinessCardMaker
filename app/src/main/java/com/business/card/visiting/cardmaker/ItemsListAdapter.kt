package com.business.card.visiting.cardmaker

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.business.card.visiting.cardmaker.widget_utils.DragableView
import kotlinx.android.synthetic.main.items_list_item.view.*

class ItemsListAdapter (var context:Context,var widgetList: ArrayList<DragableView>, private val itemsListInterface: ItemsListInterface,private val itemsListInterfaceForText: ItemsListInterface) : RecyclerView.Adapter<ItemsListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.items_list_item, parent, false)
        return ItemsListViewHolder(v)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemsListViewHolder, position: Int) {
        holder.bindItems(context,widgetList[position] )
        holder.itemView.widget_visible.setOnClickListener{
            itemsListInterface.onClick(widgetList[position], holder.itemView.widget_visible)

        }
        holder.itemView.setOnClickListener {
            itemsListInterfaceForText.onClick(widgetList[position],holder.itemView.widget_visible)
        }

    }

    override fun getItemCount(): Int {
        return widgetList.size
    }
}