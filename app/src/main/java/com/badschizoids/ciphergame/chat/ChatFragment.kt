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
import androidx.lifecycle.observe
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
//        val nameChat = preferences.getString(nameChat, null)
//        val lastMessage = preferences.getString(lastMessage, null)
//        if (nameChat != null && lastMessage != null){
//            messageAdapter = MessageAdapter(requireContext())
//        }
        if (User.notNewFrame){
            chat = User.chat
            messageAdapter.messages = User.messages
            User.notNewFrame = false
        }
        User.notNewFrame = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                  container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (requireActivity() as MainActivity).exitWork()
        Log.e("data", arguments.toString())
        if (arguments != null && User.notNewFrame) {
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
        (requireActivity() as MainActivity).setNewTitle(chat.name)
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        messagesView = view.findViewById(R.id.messages_view)

//        messageAdapter = MessageAdapter(requireContext())
        val mutableLiveData = MutableLiveData(messageAdapter)
        val dataCompany = MemberData(name, getRandomColor())
        val button = view.findViewById<MaterialButton>(R.id.send)
        if (User.notNewFrame && messageAdapter.messages.isNotEmpty())
            position = getLastPositionUser()
        if (position+1 < chat.stringsUser.size)
            button.text = chat.stringsUser[position+1].message
        else
            button.text = requireContext().resources.getString(R.string.next_chat)
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
        messagesView.adapter = messageAdapter
        messagesView.setSelection(messagesView.count - 1)

        mutableLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            var position = -1
            if (!it.isEmpty)
                position = getLastPositionUser()
            if (it.isEmpty || checkUserMessage()) {
                launch {
                    delay(1000)
                    if (position + 1 < chat.stringsCompany.size) {
                        messageAdapter.messages.add(
                                Message(
                                        chat.stringsCompany[position + 1],
                                        dataCompany,
                                        false,
                                )
                        )
                        if (chat.stringsCompany[position + 1]
                                == StoryTail.stringsCompany[StoryTail.getHelpsCompany]?.get(1)) {
                            (requireActivity() as MainActivity).newHelp()
                            User.setHelpMessages.postValue(true)
                        }
                        messagesView.adapter = messageAdapter
                        messagesView.setSelection(messagesView.count - 1)
                    }
                }
            }
        })

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
        var nameCompany = ""
        var nameNewChat = ""
        var userStrings = ""
        var companyStrings = ""
        when (nameChat) {
            Chat.nameStart -> {
                nameCompany = StoryTail.nameCompanyGetHelpsAndFirstWork
                nameNewChat = Chat.nameGetHelps
                userStrings = StoryTail.getHelpsUser
                companyStrings = StoryTail.getHelpsCompany
            }
            Chat.nameGetHelps -> {
                if (error != "") {
                    nameCompany = StoryTail.nameCompanyGetHelpsAndFirstWork
                    nameNewChat = Chat.nameFisrtWork
                    userStrings = StoryTail.firstWorkUser
                    companyStrings = StoryTail.firstWorkCompany
                } else
                    Toast.makeText(requireContext(), HelpFragment.error, Toast.LENGTH_LONG).show()
            }
            Chat.nameFisrtWork ->{
                nameCompany = StoryTail.nameCompanyStart
                nameNewChat = Chat.nameSecondWork
                userStrings = StoryTail.secondWorkUser
                companyStrings = StoryTail.secondWorkCompany
            }
            Chat.nameSecondWork ->{
                nameCompany = StoryTail.nameCompanyGetHelpsAndFirstWork
                nameNewChat = Chat.nameThirdWork
                userStrings = StoryTail.thirdWorkUser
                companyStrings = StoryTail.thirdWorkCompany
            }
            Chat.nameThirdWork -> {
                nameCompany = StoryTail.nameCompanyStart
                nameNewChat = Chat.nameFourtyWork
                userStrings = StoryTail.fourthWorkUser
                companyStrings = StoryTail.fourthWorkCompany
            }
            Chat.nameFourtyWork ->{
                nameCompany = StoryTail.nameCompanyGetHelpsAndFirstWork
                nameNewChat = Chat.nameFifthWork
                userStrings = StoryTail.fifthWorkUser
                companyStrings = StoryTail.fifthWorkCompany
            }
            Chat.nameFifthWork ->{
                nameCompany = StoryTail.nameCompanyGetHelpsAndFirstWork
                nameNewChat = Chat.nameLastWork
                userStrings = StoryTail.lastWorkUser
                companyStrings = StoryTail.lastWorkCompany
            }
        }
        bundle.putString(nameCompanyTag, nameCompany)
        bundle.putString(nameTag, nameNewChat)
        bundle.putString(stringsUserTag, userStrings)
        bundle.putString(stringsCompanyTag, companyStrings)
        User.notNewFrame = false
        if (!bundle.isEmpty)
            findNavController().navigate(R.id.action_chatFragment_self, bundle)
    }

    override fun onStop() {
        super.onStop()
        if (User.notNewFrame) {
            User.chat = chat
            User.messages = messageAdapter.messages
        }
    }

    companion object{
        val nameCompanyTag = "nameCompany"
        val nameTag = "name"
        val stringsUserTag = "StringsUser"
        val stringsCompanyTag = "StringsCompany"
        val nameChat = "nameChat"
        val lastMessage = "lastMessage"
    }

    fun getLastPositionUser(): Int {
        var position = -1
        for (i in messageAdapter.messages.size-1 downTo 0 step 1){
            if (messageAdapter.messages[i].memberData.name == "Игрок") {
                position = i
                break
            }
        }
    return position/2
    }

    fun checkUserMessage() = messageAdapter.messages.last().memberData.name == "Игрок"
}