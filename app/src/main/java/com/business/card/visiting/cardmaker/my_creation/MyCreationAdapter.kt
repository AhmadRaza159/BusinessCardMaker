package com.business.card.visiting.cardmaker.my_creation

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.business.card.visiting.cardmaker.R
import kotlinx.android.synthetic.main.item_downloaded.view.*
import java.io.File

class MyCreationAdapter (var dataList: ArrayList<File>,var flag:Boolean,var cntx:Context, private val myInterfaceForView: DownloadedInterface, private val myInterfaceForShare: DownloadedInterface): RecyclerView.Adapter<MyCreationViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCreationViewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_downloaded, parent, false)
        return MyCreationViewholder(v)
    }
    override fun onBindViewHolder(holder: MyCreationViewholder, position: Int) {
        holder.bindItems(dataList.get(position).toString())
        holder.itemView.setOnClickListener {
            myInterfaceForView.onClick(dataList[position])
        }
        holder.itemView.share_file.setOnClickListener {
            myInterfaceForShare.onClick(dataList[position])
        }
        if (flag){
            holder.itemView.item_downloaded_img.setImageDrawable(cntx.resources.getDrawable(R.drawable.my_creation_jpeg))
        }else{
            holder.itemView.item_downloaded_img.setImageDrawable(cntx.resources.getDrawable(R.drawable.my_creation_pdf))

        }
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}