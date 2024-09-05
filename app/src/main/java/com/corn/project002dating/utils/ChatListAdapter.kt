package com.corn.project002dating.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.corn.project002dating.R

class ChatListAdapter (
    private val chatRooms: List<ChatRoom>,
    private val onItemClick: (ChatRoom) -> Unit
) : RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_list, parent, false)
        return ChatListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        val chatRoom = chatRooms[position]
        holder.bind(chatRoom, onItemClick)
    }

    override fun getItemCount(): Int = chatRooms.size

    class ChatListViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(chatRoom: ChatRoom, onItemClick: (ChatRoom) -> Unit) {
            itemView.findViewById<TextView>(R.id.chat_nickname).text = chatRoom.name
            itemView.setOnClickListener { onItemClick(chatRoom) }
        }
    }
}