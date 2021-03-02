package com.badschizoids.ciphergame.tools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badschizoids.ciphergame.R

class ChatAdapter: RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    var chatMessages = mutableListOf<ChatMessage>()

    fun addMessage(chatMessage: ChatMessage){
        chatMessages.add(chatMessage)
        notifyDataSetChanged()
    }

    fun addMessage(chatMessages: MutableList<ChatMessage>){
        chatMessages.addAll(chatMessages)
    }

    fun setMessages(chatMessages: MutableList<ChatMessage>){
        this.chatMessages = chatMessages
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.chat_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(chatMessages[position])
    }

    override fun getItemCount(): Int = chatMessages.size

    inner class ChatViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(chatMessage: ChatMessage){

        }
    }
}