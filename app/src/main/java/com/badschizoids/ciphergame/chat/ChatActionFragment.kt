package com.badschizoids.ciphergame.chat

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.badschizoids.ciphergame.GenerateEncryptMessage
import com.badschizoids.ciphergame.MainActivity
import com.badschizoids.ciphergame.R
import com.badschizoids.ciphergame.ciphers.*
import com.badschizoids.ciphergame.tools.AlertsDialog
import com.badschizoids.ciphergame.tools.BaseFragment
import com.badschizoids.ciphergame.tools.Level
import com.badschizoids.ciphergame.tools.User
import com.badschizoids.ciphergame.ui.mainaction.MainActionViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class ChatActionFragment: BaseFragment() {
    lateinit var generateEncryptMessage : GenerateEncryptMessage
    val viginerCipher = ViginerCipher()
    val caeserCipher = CaesarCipher()
    val reverseCipher = ReverseCipher()
    val byteCipher = ByteCipher()
    val spinnerCipher = SpinnerCipher()
    lateinit var messageTextView : MaterialTextView
    val historyMessage = mutableSetOf<String>()
    var count = 0
    val mainActionViewModel: MainActionViewModel by lazy {
        ViewModelProvider(this).get(MainActionViewModel::class.java)
    }
    val mutableLiveData = MutableLiveData(false)
    val mutableLiveDataThird = MutableLiveData(false)
    val string = "Важный длинный текст, где много-много буков, очень длинный"
    var level = Level.NO
    val timer = object: CountDownTimer(60000, 100){
        override fun onTick(millisUntilFinished: Long) {
            Log.e("time", millisUntilFinished.toString())
        }

        override fun onFinish() {
            mutableLiveData.postValue(true)
        }
    }
    val last = "За столько секунд кровь совершает полный оборот в организме и столько ударов нанесли Цезарю. Назовите ответ."

    val timerThird = object: CountDownTimer(20000, 100){
        override fun onTick(millisUntilFinished: Long) {
            Log.e("time", millisUntilFinished.toString())
        }

        override fun onFinish() {
            mutableLiveDataThird.postValue(true)
        }
    }

    val stringsThird = mutableListOf("Ключ у Антона", "Загнивающий запад")
    var currentMessage = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null){
            level = when(arguments?.getString("level")){
                Level.NO.name -> Level.NO
                Level.FIRST.name -> Level.FIRST
                Level.SECOND.name -> Level.SECOND
                Level.THIRD.name -> Level.THIRD
                Level.FOURTH.name -> Level.FOURTH
                Level.LAST.name -> Level.LAST
                else -> Level.NO
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chat_action, container, false)
        (requireActivity() as MainActivity).toWork()
        val decryptViginerButton : MaterialButton = view.findViewById(R.id.button_viginer)
        val decryptCaeserButton : MaterialButton = view.findViewById(R.id.button_ceaser)
        val decryptReverseButton : MaterialButton = view.findViewById(R.id.button_reverse)
        val decryptSpinnerButton : MaterialButton = view.findViewById(R.id.spinner_button)
        val decryptXorButton : MaterialButton = view.findViewById(R.id.byte_button)
        messageTextView = view.findViewById(R.id.message)
        generateEncryptMessage = GenerateEncryptMessage(requireContext())
        when(level) {
            Level.FIRST -> {
                val ciphers = arrayOf(ReverseCipher(), SpinnerCipher())
                if (Random.nextBoolean())
                    ciphers.reverse()
                setButtonInActive(decryptViginerButton)
                setButtonInActive(decryptCaeserButton)
                setButtonInActive(decryptXorButton)
                mainActionViewModel.init()
                User.mutableLiveData.observe(viewLifecycleOwner) {
                    if (it.isNotEmpty()) {
                        if (User.haveThisMemes.size != it.size) {
                            val mem = (it - User.haveThisMemes.toList()).random()
                            if (!User.haveThisMemes.contains(mem)) {
                                if (mainActionViewModel.messageAnswer == "") {
                                    mainActionViewModel.messageAnswer = mem.text
                                    User.haveThisMemes.add(mem)
                                }
                                setMessage(
                                        generateMessage(mainActionViewModel.messageAnswer, ciphers)
                                )
                                User.helps.forEach {
                                    it.show()
                                }
                                User.helps.clear()
                            }
                        }
                    }
                }
            }
            Level.SECOND -> {
                val ciphers = arrayOf(CaesarCipher(5), ViginerCipher("Мем"))
                if (Random.nextBoolean())
                    ciphers.reverse()
                setButtonInActive(decryptReverseButton)
                setButtonInActive(decryptSpinnerButton)
                setButtonInActive(decryptXorButton)
                val string = "Важный длинный текст, где много-много буков, очень длинный"
                setMessage(generateMessage(string, ciphers))
                timer.start()
                mutableLiveData.observe(viewLifecycleOwner, Observer {
                    if (it) {
                        AlertsDialog().createLoseAlertDialog(requireContext()).show()
                        launch {
                            delay(500)
                            findNavController().navigate(R.id.action_chatActionFragment_to_chatFragment)
                        }
                    }
                })
            }
            Level.THIRD -> {
                timerThird.start()
                val ciphers = arrayOf(CaesarCipher(4), ViginerCipher("Сахар"))
                setButtonInActive(decryptReverseButton)
                setButtonInActive(decryptSpinnerButton)
                setButtonInActive(decryptXorButton)
                val newStrings = stringsThird.minus(User.haveThisMemesThird.toTypedArray())
                if (newStrings.isNotEmpty()) {
                    currentMessage = newStrings.random()
                    setMessage(generateMessage(currentMessage, ciphers))
                    mutableLiveDataThird.observe(viewLifecycleOwner, Observer {
                        if (it) {
                            Toast.makeText(requireContext(), "Стоит читать диалоги :)\n " +
                                    "Ключ для шифра Цезаря - 4, для Вижинера - Сахар",
                                    Toast.LENGTH_LONG).show()
                            timer.start()
                            mutableLiveDataThird.postValue(false)
                        }
                    })
                }
            }
            Level.FOURTH -> {
                setButtonInActive(decryptViginerButton)
                setButtonInActive(decryptCaeserButton)
                setButtonInActive(decryptXorButton)
                setButtonInActive(decryptReverseButton)
                setButtonInActive(decryptSpinnerButton)
                setMessage(generateMessage(last, arrayOf()))
                timerThird.start()
                mutableLiveDataThird.observe(viewLifecycleOwner, Observer {
                    if (it) {
                        Toast.makeText(requireContext(), "Как решите, просто вернитесь назад",
                                Toast.LENGTH_LONG).show()
                    }
                })
            }
            Level.LAST -> {
                setButtonInActive(decryptViginerButton)
                setButtonInActive(decryptCaeserButton)
                setButtonInActive(decryptXorButton)
                setButtonInActive(decryptReverseButton)
                setButtonInActive(decryptSpinnerButton)
                val mut = MutableLiveData(0)
                val viewG = layoutInflater.inflate(R.layout.ket_caesar, null)
                AlertDialog.Builder(requireContext())
                        .setView(viewG)
                        .setPositiveButton("Завершить") { dialog, which ->
                            mut.postValue(viewG.findViewById<EditText>(R.id.email).text.toString().toInt())
                            dialog.cancel()
                        }.create().show()
                mut.observe(viewLifecycleOwner, {
                    if (it != 0){
                        val bundle = Bundle()
                        bundle.putInt("finish", it)
                        findNavController().navigate(R.id.action_chatActionFragment_to_finishFragment, bundle)
                    }
                })
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
                when(level){
                    Level.FIRST ->{
                        if (mainActionViewModel.messageAnswer == s.toString()) {
                            launch {
                                delay(1000)
                                AlertsDialog().createSussesAlertDialog(requireContext()).show()
                                if (User.haveThisMemes.size == User.mutableLiveData.value?.size)
                                    findNavController()
                                            .navigate(R.id.action_chatActionFragment_to_chatFragment)
                                else{
                                    val bundle = Bundle()
                                    bundle.putString("level", level.name)
                                    findNavController()
                                            .navigate(R.id.action_chatActionFragment_self, bundle)
                                }
                            }
                        }
                    }
                    Level.THIRD ->{
                        if (currentMessage.equals(s.toString(), ignoreCase = true)){
                            User.haveThisMemesThird.add(s.toString())
                            launch {
                                delay(1000)
                                AlertsDialog().createSussesAlertDialog(requireContext()).show()
                                if (User.haveThisMemesThird.size == stringsThird.size)
                                    findNavController()
                                            .navigate(R.id.action_chatActionFragment_to_chatFragment)
                                else{
                                    val bundle = Bundle()
                                    bundle.putString("level", level.name)
                                    findNavController()
                                            .navigate(R.id.action_chatActionFragment_self, bundle)
                                }
                            }
                        }
                    }
                    Level.FOURTH ->{
                        if (last.equals(s.toString(), true)){
                            launch {
                                delay(1000)
                                AlertsDialog().createSussesAlertDialog(requireContext()).show()
                                findNavController()
                                        .navigate(R.id.action_chatActionFragment_to_chatFragment)
                            }
                        }
                    }
                }

            }
        })

        decryptViginerButton.setOnClickListener {
            if (level == Level.SECOND || level == Level.THIRD){
                AlertsDialog().createInsertKeyViginer(requireContext()).show()
                User.mutableLiveDataKeyViginer.observe(viewLifecycleOwner, Observer {
                    if (it != User.keyViginer){
                        viginerCipher.key = it
                        setMessage(viginerCipher.decrypt(messageTextView.text.toString()))
                    }
                })

            }
            else
                setMessage(viginerCipher.decrypt(messageTextView.text.toString()))
        }
        decryptCaeserButton.setOnClickListener {
            if (level == Level.SECOND || level == Level.THIRD){
                AlertsDialog().createInsertKeyCaesar(requireContext()).show()
                User.mutableLiveDataKeyCaesar.observe(viewLifecycleOwner, {
                    if (it != User.keyCaesar){
                        caeserCipher.key = it
                        setMessage(caeserCipher.decrypt(messageTextView.text.toString()))
                    }
                })
            }
            else
                setMessage(caeserCipher.decrypt(messageTextView.text.toString()))
        }
        decryptReverseButton.setOnClickListener {
            setMessage(reverseCipher.decrypt(messageTextView.text.toString()))
        }
        decryptSpinnerButton.setOnClickListener {
            setMessage(spinnerCipher.decrypt(messageTextView.text.toString()))
        }
        decryptXorButton.setOnClickListener {
            setMessage(byteCipher.decrypt(messageTextView.text.toString()))
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

    fun setButtonInActive(materialButton: MaterialButton){
        materialButton.isEnabled = false
        materialButton.text = "?"
        materialButton.setTextColor(Color.WHITE)
        materialButton.setBackgroundColor(Color.BLACK)
    }

    fun generateMessage(string: String, array: Array<EncryptAndDecrypt>): String {
        var message = string
        array.forEach {
            message = it.encrypt(message)
        }
        return message
    }
}