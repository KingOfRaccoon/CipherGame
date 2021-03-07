package com.badschizoids.ciphergame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text


class FinishFragment : Fragment() {
    var answer = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null)
            answer = arguments?.getInt("finish", 0)!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_finish, container, false)
        val text : TextView = view.findViewById(R.id.finish)
        if (answer == 23)
            text.text = "Эти данные оказались крайне важны для “Индекса”, с помощью них корпорация быстро превосходит “Стеллу” и уводит ее с рынка. Вас же взяли на работу и даже выплатили премию. Казалось бы, все идет хорошо, но компании оказались не нужны шифровщики в таком количестве, и вас сократили. “Индекс” стал монополией и не предоставлял вакансий по вашей специальности, достойную работу вы найти так и не смогли."
        else
            text.text = "Вас с треском уволили с работы. Вы находите новую работу, однако она прикрывается через несколько месяцев: данные были рассекречены другими сотрудниками, с их помощью “Индекс” увел с рынка “Стелу”  и начал выдавливать мелкие компании, среди которых оказалась и ваша. По высокотехнологичным специальностям вакансии предоставляет только “Индекс”, а туда вам путь закрыт. Вы остались на низкооплачиваемой работе без надежного будущего. "

        return view
    }

}