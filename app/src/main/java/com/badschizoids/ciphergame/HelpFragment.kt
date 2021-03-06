package com.badschizoids.ciphergame

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badschizoids.ciphergame.tools.HelpMessage
import com.badschizoids.ciphergame.tools.HelpMessageAdapter
import com.badschizoids.ciphergame.tools.User

class HelpFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)
        val preferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        preferences.edit().putString("checkHelp", error).apply()
        val helpMessageAdapter = HelpMessageAdapter(requireContext())
        val recyclerView : RecyclerView = view.findViewById(R.id.recycler)
        if (!preferences.getBoolean("helps", false)) {
            User.setHelpMessages.observe(viewLifecycleOwner) {
                if (it) {
                    helpMessageAdapter.strings = mutableListOf(
                            HelpMessage("Шифр Цезаря", "file:///android_asset/Caeser.html"),
                            HelpMessage("Шифр Виженера", "file:///android_asset/Vishiner.html")
                    )
                    recyclerView.adapter = helpMessageAdapter
                    preferences.edit().putBoolean("helps", true).apply()
                }
            }
        }
        else{

            helpMessageAdapter.strings = mutableListOf(
                    HelpMessage("Шифр Цезаря", "file:///android_asset/Caeser.html"),
                    HelpMessage("Шифр Виженера", "file:///android_asset/Vishiner.html")
            )
            recyclerView.adapter = helpMessageAdapter
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }
    companion object{
        val helpMessages = mutableListOf(
                HelpMessage("Шифр Цезаря", "file:///android_asset/Caeser.html"),
                HelpMessage("Шифр Виженера", "file:///android_asset/Vishiner.html")
        )
        val error = "Вы не просмотрели справки"
    }
}