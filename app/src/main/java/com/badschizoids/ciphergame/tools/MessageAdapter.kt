package com.badschizoids.ciphergame.tools

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.navigation.findNavController
import com.badschizoids.ciphergame.R
import com.google.android.material.button.MaterialButton


class MessageAdapter(var context: Context) : BaseAdapter() {
    var messages: MutableList<Message> = ArrayList()
    fun add(message: Message) {
        messages.add(message)
        notifyDataSetChanged() // to render the list we need to notify
    }

    fun clear(){
        messages.clear()
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return messages.size
    }

    override fun getItem(i: Int): Any {
        return messages[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    // This is the backbone of the class, it handles the creation of single ListView row (chat bubble)
    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val view: View
        val holder = MessageViewHolder()
        val messageInflater = LayoutInflater.from(context)
        val message = messages[i]
        if (message.belongsToCurrentUser) { // this message was sent by us so let's create a basic chat bubble on the right
            view = messageInflater.inflate(R.layout.my_message, null)
            holder.messageBody = view.findViewById(R.id.message_body)
            view.tag = holder
            holder.messageBody!!.text = message.text
        } else { // this message was sent by someone else so let's create an advanced chat bubble on the left
            if (message.needWork) {
                view = messageInflater.inflate(R.layout.their_message_work, null)
                val button : MaterialButton = view.findViewById(R.id.nextWork)
                button.setOnClickListener{
                    val bundle = Bundle()
                    bundle.putString("level", message.level.name)
                    it.findNavController()
                        .navigate(R.id.action_chatFragment_to_chatActionFragment, bundle)
                }
            }
            else
                view = messageInflater.inflate(R.layout.their_message, null)
            holder.avatar = view.findViewById(R.id.avatar) as View
            holder.name = view.findViewById(R.id.name)
            holder.messageBody = view.findViewById(R.id.message_body)
            view.tag = holder
            holder.name!!.text = message.memberData.name
            holder.messageBody!!.text = message.text
            val drawable = holder.avatar!!.background as GradientDrawable
            drawable.setColor(Color.parseColor(message.memberData.color))
        }
        return view
    }
}

internal class MessageViewHolder {
    var avatar: View? = null
    var name: TextView? = null
    var messageBody: TextView? = null
}