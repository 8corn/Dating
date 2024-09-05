package com.corn.project002dating.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.corn.project002dating.R

class CardStackAdapter (
    private val context: Context,
    val items: MutableList<Person>
): RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nametxt)
        val ageTextView: TextView = itemView.findViewById(R.id.agetxt)
        val placeTextView: TextView = itemView.findViewById(R.id.plctxt)
        val imageView: ImageView = itemView.findViewById(R.id.overlay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item =items[position]
        holder.nameTextView.text = item.name
        holder.ageTextView.text = item.age.toString()
        holder.placeTextView.text = item.place

        val imageResource = item.imageResId
        holder.imageView.setImageResource(imageResource)

        Log.d("CardStackAdapter", "Binding data: $item with image resource: $imageResource")
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(newItems: List<Person>) {
        Log.d("CardStackAdapter", "Adding items: $newItems")
        val startPosition = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(startPosition,newItems.size)
    }
}