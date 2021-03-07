package com.badschizoids.ciphergame.chat

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.badschizoids.ciphergame.GenerateEncryptMessage
import com.badschizoids.ciphergame.MainActivity
import com.badschizoids.ciphergame.R
import com.badschizoids.ciphergame.ciphers.CaesarCipher
import com.badschizoids.ciphergame.ciphers.ReverseCipher
import com.badschizoids.ciphergame.ciphers.ViginerCipher
import com.badschizoids.ciphergame.tools.AlertsDialog
import com.badschizoids.ciphergame.tools.BaseFragment
import com.badschizoids.ciphergame.tools.Level
import com.badschizoids.ciphergame.tools.User
import com.badschizoids.ciphergame.ui.mainaction.MainActionViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.prefs.Preferences

class ChatActionFragment: BaseFragment() {
    lateinit var generateEncryptMessage : GenerateEncryptMessage
    val viginerCipher = ViginerCipher()
    val caeserCipher = CaesarCipher()
    val reverseCipher = ReverseCipher()
    lateinit var messageTextView : MaterialTextView
    val historyMessage = mutableSetOf<String>()
    var count = 0
    val mainActionViewModel: MainActionViewModel by lazy {
        ViewModelProvider(this).get(MainActionViewModel::class.java)
    }
    var level = Level.NO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null){
            when(arguments?.getString("level")){
                Level.NO.name -> level = Level.NO
                Level.FIRST.name -> level = Level.FIRST
                Level.SECOND.name -> level = Level.SECOND
                Level.THIRD.name -> level = Level.THIRD
                Level.FOURTH.name -> level = Level.FOURTH
                else -> level = Level.NO
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chat_action, container, false)
        (requireActivity() as MainActivity).toWork()
        val decryptViginerButton : MaterialButton = view.findViewById(R.id.button_viginer)
        val decryptCaeserButton : MaterialButton = view.findViewById(R.id.button_ceaser)
        val decryptReverseButton : MaterialButton = view.findViewById(R.id.button_reverse)
        when(level){
            Level.FIRST ->{
                mainActionViewModel.init()
                generateEncryptMessage = GenerateEncryptMessage(requireContext())
                messageTextView = view.findViewById(R.id.message)
                User.mutableLiveData.observe(viewLifecycleOwner) {
                    if (it.isNotEmpty()) {
                        if (User.haveThisMemes.size != it.size) {
                            val mem = (it - User.haveThisMemes.toList()).random()
                            if (!User.haveThisMemes.contains(mem)) {
                                if (mainActionViewModel.messageAnswer == "") {
                                    mainActionViewModel.messageAnswer = mem.text
                                    User.haveThisMemes.add(mem)
                                }
                                setMessage(generateEncryptMessage
                                    .generateMessage(mainActionViewModel.messageAnswer, 1))
                                User.helps.forEach {
                                    it.show()
                                }
                                User.helps.clear()
                            }
                        }
                    }
                }
            }
        }


        messageTextView.setOnClickListener {
            if (historyMessage.isNotEmpty()){
                setMessage(historyMessage.last())
                historyMessage.remove(historyMessage.toList()[historyMessage.size-1])
            }
        }

        messageTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (mainActionViewModel.messageAnswer == s.toString()) {
                    launch {
                        delay(1000)
                        AlertsDialog().createSussesAlertDialog(requireContext()).show()
                        if (User.haveThisMemes.size == User.mutableLiveData.value?.size)
                            findNavController().navigate(R.id.action_chatActionFragment_to_chatFragment)
                        else
                            findNavController().navigate(R.id.action_chatActionFragment_self)
                    }
                }
            }
        })

        decryptViginerButton.setOnClickListener {
            setMessage(viginerCipher.decrypt(messageTextView.text.toString()))
        }
        decryptCaeserButton.setOnClickListener {
            setMessage(caeserCipher.decrypt(messageTextView.text.toString()))
        }
        decryptReverseButton.setOnClickListener {
            setMessage(reverseCipher.decrypt(messageTextView.text.toString()))
        }

        return view
    }
    fun setMessage(string: String) {
        if (messageTextView.text.isNotEmpty() && messageTextView.text.isNotBlank()) {
            if (messageTextView.text.toString() != "TextView")
                historyMessage.add(messageTextView.text.toString())
            messageTextView.text = string
            count++
        }
    }

}