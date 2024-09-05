package com.corn.project002dating.utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.corn.project002dating.R
import com.corn.project002dating.main.ChatLayout

class ChatAdapter(val currentUser: String, val itemList: ArrayList<ChatLayout>): RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_chat_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ChatAdapter.ViewHolder, position: Int) {
        if (currentUser == itemList[position].nickname) {
            holder.card.setCardBackgroundColor(Color.parseColor("#FFF176"))
        }
        holder.nickname.text = itemList[position].nickname
        holder.contents.text = itemList[position].contents
        holder.time.text = itemList[position].time
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.chat_card_view)
        val nickname: TextView = itemView.findViewById(R.id.chat_tv_nickname)
        val contents: TextView = itemView.findViewById(R.id.chat_tv_contents)
        val time: TextView = itemView.findViewById(R.id.chat_tv_time)
    }
}