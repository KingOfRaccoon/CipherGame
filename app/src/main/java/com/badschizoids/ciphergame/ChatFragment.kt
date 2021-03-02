package com.badschizoids.ciphergame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.badschizoids.ciphergame.tools.MessageAdapter
import com.example.chat.MemberData
import com.example.chat.Message
import java.util.*
import kotlin.math.floor

class ChatFragment: Fragment() {
    private lateinit var editText: EditText
    lateinit var messageAdapter : MessageAdapter
    lateinit var messagesView : ListView
    override fun onCreateView(inflater: LayoutInflater,
                  container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        val data = MemberData(getRandomName(), getRandomColor())
        editText = view.findViewById<EditText>(R.id.editText) as EditText
        view.findViewById<ImageButton>(R.id.send).setOnClickListener {
            val message = editText.text.toString()
            if (message.isNotEmpty()) {
                requireActivity().runOnUiThread {
                    messageAdapter.add(Message(message, data, true))
                    messagesView.adapter = messageAdapter
                    messagesView.setSelection(messagesView.count - 1)
                }
                editText.text.clear()
            }
        }
        messagesView = view.findViewById(R.id.messages_view)
        messageAdapter = MessageAdapter(requireContext())
        messagesView.adapter = MessageAdapter(requireContext())
        messagesView.setSelection(messagesView.count - 1)



        return view
    }
    private fun getRandomName(): String {
        val adjs = arrayOf(
                "autumn",
                "hidden",
                "bitter",
                "misty",
                "silent",
                "empty",
                "dry",
                "dark",
                "summer",
                "icy",
                "delicate",
                "quiet",
                "white",
                "cool",
                "spring",
                "winter",
                "patient",
                "twilight",
                "dawn",
                "crimson",
                "wispy",
                "weathered",
                "blue",
                "billowing",
                "broken",
                "cold",
                "damp",
                "falling",
                "frosty",
                "green",
                "long",
                "late",
                "lingering",
                "bold",
                "little",
                "morning",
                "muddy",
                "old",
                "red",
                "rough",
                "still",
                "small",
                "sparkling",
                "throbbing",
                "shy",
                "wandering",
                "withered",
                "wild",
                "black",
                "young",
                "holy",
                "solitary",
                "fragrant",
                "aged",
                "snowy",
                "proud",
                "floral",
                "restless",
                "divine",
                "polished",
                "ancient",
                "purple",
                "lively",
                "nameless"
        )
        val nouns = arrayOf(
                "waterfall",
                "river",
                "breeze",
                "moon",
                "rain",
                "wind",
                "sea",
                "morning",
                "snow",
                "lake",
                "sunset",
                "pine",
                "shadow",
                "leaf",
                "dawn",
                "glitter",
                "forest",
                "hill",
                "cloud",
                "meadow",
                "sun",
                "glade",
                "bird",
                "brook",
                "butterfly",
                "bush",
                "dew",
                "dust",
                "field",
                "fire",
                "flower",
                "firefly",
                "feather",
                "grass",
                "haze",
                "mountain",
                "night",
                "pond",
                "darkness",
                "snowflake",
                "silence",
                "sound",
                "sky",
                "shape",
                "surf",
                "thunder",
                "violet",
                "water",
                "wildflower",
                "wave",
                "water",
                "resonance",
                "sun",
                "wood",
                "dream",
                "cherry",
                "tree",
                "fog",
                "frost",
                "voice",
                "paper",
                "frog",
                "smoke",
                "star"
        )
        return adjs[floor(Math.random() * adjs.size).toInt()] +
                "_" +
                nouns[floor(Math.random() * nouns.size).toInt()]
    }

    private fun getRandomColor(): String {
        val r = Random()
        val sb = StringBuffer("#")
        while (sb.length < 7) {
            sb.append(Integer.toHexString(r.nextInt()))
        }
        return sb.toString().substring(0, 7)
    }
}