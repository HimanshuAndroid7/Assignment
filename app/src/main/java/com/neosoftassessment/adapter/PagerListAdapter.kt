package com.neosoftassessment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neosoftassessment.R
import kotlinx.android.synthetic.main.item_list.view.*
import java.util.*
import kotlin.collections.ArrayList

class PagerListAdapter() :
    RecyclerView.Adapter<PagerListAdapter.ListViewHolder>() {

    private val list: ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,
                false)
        )
    }

    fun updateList(query: String, list: ArrayList<String>) {
        this.list.clear()
        if (query.isNotEmpty())
            this.list.addAll(list.filter {
                it.lowercase(Locale.getDefault()).contains(query) || it.lowercase(
                    Locale.getDefault()
                ).contains(query)
            })
        else
            this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(position,list)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pos:Int,list: List<String>) {
            itemView.txt_list.text = list[pos]
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}