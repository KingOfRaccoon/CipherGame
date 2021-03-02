package com.badschizoids.ciphergame

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.badschizoids.ciphergame.ciphers.CaesarCipher
import com.badschizoids.ciphergame.ciphers.ReverseCipher
import com.badschizoids.ciphergame.ciphers.ViginerCipher
import com.badschizoids.ciphergame.network.DataFireStore
import com.badschizoids.ciphergame.saveriddle.RiddleDataBase
import com.badschizoids.ciphergame.tools.*
import com.badschizoids.ciphergame.ui.mainaction.MainActionViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.launch

class MainActionFragment : BaseFragment() {

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
    lateinit var database: RiddleDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        database = CipherApplication().getDatabase()
        if (User.mutableLiveData.value.isNullOrEmpty())
            mainActionViewModel.init()
        generateEncryptMessage = GenerateEncryptMessage(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        database.solvedRiddleDao().getAllHistory().observe(
//            viewLifecycleOwner, Observer {
//                it.forEach {
//                    Log.e("Data", it.toString())
//                }
//            }
//        )
        val view = inflater.inflate(R.layout.fragment_main_action, container, false)
        val decryptViginerButton : MaterialButton = view.findViewById(R.id.button_viginer)
        val decryptCaeserButton : MaterialButton = view.findViewById(R.id.button_ceaser)
        val decryptReverseButton : MaterialButton = view.findViewById(R.id.button_reverse)
        messageTextView = view.findViewById(R.id.message)
        User.mutableLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                if (mainActionViewModel.messageAnswer == "")
                    mainActionViewModel.messageAnswer = it.random().text
                setMessage(generateEncryptMessage
                        .generateMessage(mainActionViewModel.messageAnswer, 1))
                User.helps.forEach {
                    it.show()
                }
                User.helps.clear()
            }
        })

        messageTextView.setOnClickListener {
            if (historyMessage.isNotEmpty()){
                setMessage(historyMessage.last())
                historyMessage.remove(historyMessage.toList()[historyMessage.size-1])
            }
        }

        messageTextView.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (mainActionViewModel.messageAnswer == s.toString()) {
                    AlertsDialog().createSussesAlertDialog(requireContext()).show()
                    mainActionViewModel.messageAnswer = ""
//                    launch {
//                        database.solvedRiddleDao().addSolvedRiddleDao(
//                            SolvedRiddle(s.toString(), count)
//                        )
//                    }
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}