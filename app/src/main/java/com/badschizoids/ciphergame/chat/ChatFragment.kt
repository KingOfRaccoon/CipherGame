package com.badschizoids.ciphergame.chat

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.badschizoids.ciphergame.HelpFragment
import com.badschizoids.ciphergame.MainActivity
import com.badschizoids.ciphergame.R
import com.badschizoids.ciphergame.tools.*
import com.example.chat.MemberData
import com.badschizoids.ciphergame.tools.Message
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class ChatFragment: BaseFragment() {
     val messageAdapter : MessageAdapter by lazy {
         MessageAdapter(requireContext())
    }
    lateinit var messagesView : ListView
    var stringUser = StoryTail.stringsUser[StoryTail.startUser]
    var stringCompany = StoryTail.stringsCompany[StoryTail.startComapny]
    var name = StoryTail.nameCompanyStart
    var position = -1
    var chat = Chat(Chat.nameStart, stringUser!!, stringCompany!!)

    lateinit var preferences : SharedPreferences
    override fun onStart() {
        super.onStart()
        preferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val nameChat = preferences.getString(nameChat, null)
        val lastMessage = preferences.getString(lastMessage, null)
        if (nameChat != null && lastMessage != null){
//            messageAdapter = MessageAdapter(requireContext())
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                  container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (requireActivity() as MainActivity).exitWork()
        Log.e("data", arguments.toString())
        if (arguments != null) {
            val companyName = arguments?.getString(nameCompanyTag)
            val chatName = arguments?.getString(nameTag)
            val arrayUser = StoryTail.stringsUser[arguments?.getString(stringsUserTag)]
            val arrayCompany = StoryTail
                    .stringsCompany[arguments?.getString(stringsCompanyTag)]
            if (chatName != null && arrayCompany != null
                    && arrayUser != null && companyName != null){
                chat = Chat(chatName, arrayUser, arrayCompany)
                name = companyName
            }
        }
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        messagesView = view.findViewById(R.id.messages_view)

//        messageAdapter = MessageAdapter(requireContext())
        val mutableLiveData = MutableLiveData(messageAdapter)
        val dataCompany = MemberData(name, getRandomColor())
        val button = view.findViewById<MaterialButton>(R.id.send)
        button.text = chat.stringsUser[position+1].message
        button.setOnClickListener {
            button.text = ""
            position++
//            val message = editText.text.toString()
            if (position < chat.stringsUser.size) {
                if (position+1< chat.stringsUser.size)
                    button.text = chat.stringsUser[position+1].message
                else {
                    launch {
                        delay(1100)
                        button.text = requireContext().resources.getString(R.string.next_chat)
                    }
                }
                val message = chat.stringsUser[position]
                if (message.message.isNotEmpty()) {
                    requireActivity().runOnUiThread {
                        messageAdapter.add(Message(message.message,
                                MemberData("Игрок", getRandomColor()),
                                true, message.needWork))
                        messagesView.adapter = messageAdapter
                        mutableLiveData.postValue(messageAdapter)
                        messagesView.setSelection(messagesView.count - 1)
                    }
                }
            }
            else
                nextChat(chat.name)
        }
        messagesView.adapter = MessageAdapter(requireContext())
        messagesView.setSelection(messagesView.count - 1)

        mutableLiveData.observe(viewLifecycleOwner) {
            var position = -1
            if (!it.isEmpty)
                position = chat.getPositionInUserString(it.messages.last().text)
            launch {
                delay(1000)
                if (position + 1 < chat.stringsCompany.size) {
                    messageAdapter.messages.add(
                            Message(
                                    chat.stringsCompany[position + 1].message,
                                    dataCompany,
                                    false,
                                    chat.stringsCompany[position + 1].needWork
                            )
                    )
                    if (chat.stringsCompany[position+1]
                            == StoryTail.stringsCompany[StoryTail.getHelpsCompany]?.get(1)) {
                        (requireActivity() as MainActivity).newHelp()
                        User.setHelpMessages.postValue(true)
                    }
                    messagesView.adapter = messageAdapter
                    messagesView.setSelection(messagesView.count - 1)
                }
            }
        }

        return view
    }
    private fun getRandomColor(): String {
        val r = Random()
        val sb = StringBuffer("#")
        while (sb.length < 7) {
            sb.append(Integer.toHexString(r.nextInt()))
        }
        return sb.toString().substring(0, 7)
    }
    fun nextChat(nameChat: String) {
        val error = PreferenceManager.getDefaultSharedPreferences(requireContext())
                .getString("checkHelp", "")
        val bundle = Bundle()
        when (nameChat) {
            Chat.nameStart -> {
                bundle.putString(nameCompanyTag, StoryTail.nameCompanyGetHelpsAndFirstWork)
                bundle.putString(nameTag, Chat.nameGetHelps)
                bundle.putString(stringsUserTag, StoryTail.getHelpsUser)
                bundle.putString(stringsCompanyTag, StoryTail.getHelpsCompany)
            }
            Chat.nameGetHelps -> {
                if (error != "") {
                    bundle.putString(nameCompanyTag, StoryTail.nameCompanyGetHelpsAndFirstWork)
                    bundle.putString(nameTag, Chat.nameFisrtWork)
                    bundle.putString(stringsUserTag, StoryTail.firstWorkUser)
                    bundle.putString(stringsCompanyTag, StoryTail.firstWorkCompany)
                } else
                    Toast.makeText(requireContext(), HelpFragment.error, Toast.LENGTH_LONG).show()
            }
        }
//        if (error == ""){
//
//        }
//        Log.e("data", bundle.toString())
        if (!bundle.isEmpty)
            findNavController().navigate(R.id.action_chatFragment_self, bundle)
    }

    companion object{
        val nameCompanyTag = "nameCompany"
        val nameTag = "name"
        val stringsUserTag = "StringsUser"
        val stringsCompanyTag = "StringsCompany"
        val nameChat = "nameChat"
        val lastMessage = "lastMessage"
    }
//    fun goToLastMessage
}