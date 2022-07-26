package com.neosoftassessment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neosoftassessment.R
import kotlinx.android.synthetic.main.image_slider_item.view.*

class ViewPagerAdapter:
    RecyclerView.Adapter<ViewPagerAdapter.ListViewHolder>() {

    private val list: ArrayList<Int> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_item, parent,
                false)
        )
    }

    fun updateList(list: ArrayList<Int>) {
        this.list.clear()
            this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(position, list)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pos: Int, list: List<Int>) {
            itemView.idIVImage.setImageResource(list[pos])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
