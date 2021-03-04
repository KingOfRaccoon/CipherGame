package com.badschizoids.ciphergame

import android.content.Context
import android.preference.PreferenceManager
import com.badschizoids.ciphergame.ciphers.*
import com.badschizoids.ciphergame.tools.AlertsDialog
import com.badschizoids.ciphergame.tools.User

class GenerateEncryptMessage(val context: Context) {
    val preferences = PreferenceManager.getDefaultSharedPreferences(context)
    fun generateMessage(string: String, level: Int): String {
        var message = string
        val ciphers = mutableListOf(CaesarCipher(), ViginerCipher(), ReverseCipher())
        var count = 0
        when(level){
            in (0..2) ->{
                count = 1
            }
            in (3..5) ->{
                count = 2
            }
        }
        for (i in 0 until count){
            val cipher = ciphers.random()
            when(cipher){
                is CaesarCipher ->{
                    val beenThisCipher = preferences.getString(preferences_caesar, "No")
                    if (beenThisCipher == "No"){
                        User.helps.add(
                                AlertsDialog().createCaesarAlertDialog(context)
                        )
                        preferences.edit().putString(preferences_caesar, "Yes").apply()
                    }
                }
                is ViginerCipher ->{
                    val beenThisCipher = preferences.getString(preferences_viginer, "No")
                    if (beenThisCipher == "No"){
                        User.helps.add(
                                AlertsDialog().createViginerAlertDialog(context)
                        )
                        preferences.edit().putString(preferences_viginer, "Yes").apply()
                    }
                }
                is ReverseCipher ->{
                    val beenThisCipher = preferences.getString(preferences_reverse, "No")
                    if (beenThisCipher == "No"){
                        User.helps.add(
                            AlertsDialog().createReverseAlertDialog(context)
                        )
                        preferences.edit().putString(preferences_reverse, "Yes").apply()
                    }
                }
                is ByteCipher ->{
                    val beenThisCipher = preferences.getString(preferences_byte, "No")
                    if (beenThisCipher == "No"){
                        User.helps.add(
                            AlertsDialog().createByteAlertDialog(context)
                        )
                        preferences.edit().putString(preferences_byte, "Yes").apply()
                    }
                }
                is SpinnerCipher ->{
                    val beenThisCipher = preferences.getString(preferences_spinner, "No")
                    if (beenThisCipher == "No"){
                        User.helps.add(
                            AlertsDialog().createSpinnerAlertDialog(context)
                        )
                        preferences.edit().putString(preferences_spinner, "Yes").apply()
                    }
                }
            }
            message = cipher.encrypt(message)
        }
        return message
    }
    companion object{
        val preferences_viginer = "Viginer"
        val preferences_caesar = "Caesar"
        val preferences_reverse = "Reverse"
        val preferences_byte = "Byte"
        val preferences_spinner = "Spinner"
    }
}