package com.badschizoids.ciphergame

import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceActivity
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.SharedPreferencesCompat
import androidx.lifecycle.Observer
import androidx.room.Database
import com.badschizoids.ciphergame.ciphers.CaeserCipher
import com.badschizoids.ciphergame.ciphers.ReverseCipher
import com.badschizoids.ciphergame.ciphers.ViginerCipher
import com.badschizoids.ciphergame.network.DataFireStore
import com.badschizoids.ciphergame.saveriddle.RiddleDataBase
import com.badschizoids.ciphergame.tools.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.launch

class MainActionFragment : Fragment() {

    val generateEncryptMessage = GenerateEncryptMessage()
    val viginerCipher = ViginerCipher()
    val caeserCipher = CaeserCipher()
    val reverseCipher = ReverseCipher()
    lateinit var messageTextView : MaterialTextView
    val historyMessage = mutableSetOf<String>()
    var messageAnswer = ""
    var count = 0
    lateinit var database: RiddleDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        database = CipherApplication().getDatabase()
        DataFireStore().getAllMemes().addOnCompleteListener {
            if (it.isSuccessful){
                val listMemes = mutableListOf<Mem>()
                val list = it.result?.get("mem") as List<String>
                for (i in list){
                    listMemes.add(Mem(i))
                }
                User.mutableLiveData.postValue(listMemes)
            }
            else
                Log.e("Data", it.exception?.message.toString())
        }
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
        Log.e("Data", caeserCipher.encrypt("This is fine"))
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_action, container, false)
        val decryptViginerButton : MaterialButton = view.findViewById(R.id.button_viginer)
        val decryptCaeserButton : MaterialButton = view.findViewById(R.id.button_caeser)
        val decryptReverseButton : MaterialButton = view.findViewById(R.id.button_reverse)
        messageTextView  = view.findViewById(R.id.message)
        User.mutableLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()){
                messageAnswer = it.random().text
                setMessage(generateEncryptMessage.generateMessage(messageAnswer, 3))
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
                if (messageAnswer == s.toString()) {
                    AlertsDialog().createSussesAlertDialog(requireContext()).show()
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
}