package com.badschizoids.ciphergame.tools

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badschizoids.ciphergame.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class HelpMessageAdapter(val context: Context): RecyclerView.Adapter<HelpMessageAdapter.HelpMessageViewHolder>() {
    var strings = mutableListOf<HelpMessage>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpMessageViewHolder {
        return HelpMessageViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.help_adapter, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HelpMessageViewHolder, position: Int) {
        holder.bind(strings[position], context)
    }

    override fun getItemCount(): Int = strings.size

    inner class HelpMessageViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cardView : MaterialCardView = view.findViewById(R.id.cardView)
        val textHelp : MaterialTextView = view.findViewById(R.id.textHelp)
        fun bind(helpMessage: HelpMessage, context: Context){
            textHelp.text = helpMessage.name
            cardView.setOnClickListener {
                AlertsDialog().createUrlAlertDialog(helpMessage.url, context).show()
            }
        }
    }
}