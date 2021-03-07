package com.badschizoids.ciphergame.tools

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.webkit.WebView
import android.widget.EditText
import com.badschizoids.ciphergame.R

class AlertsDialog {
    fun createSussesAlertDialog(context: Context): AlertDialog {
        return AlertDialog.Builder(context)
                .setTitle(context.resources.getString(R.string.title_susses))
                .setPositiveButton(R.string.close) { dialog, which ->
                    dialog.cancel()
                }
                .create()
    }

    fun createViginerAlertDialog(context: Context): AlertDialog{
        return AlertDialog.Builder(context)
                .setView(WebView(context).apply { loadUrl("file:///android_asset/Vishiner.html") })
                .setPositiveButton(R.string.close) { dialog, which ->
                    dialog.cancel()
                }
                .create()
    }

    fun createCaesarAlertDialog(context: Context): AlertDialog{
        return AlertDialog.Builder(context)
                .setView(WebView(context).apply { loadUrl("file:///android_asset/Caeser.html") })
                .setPositiveButton(R.string.close) { dialog, which ->
                    dialog.cancel()
                }
                .create()
    }

    fun createUrlAlertDialog(url: String, context: Context): AlertDialog{
        return AlertDialog.Builder(context)
                .setView(WebView(context).apply { loadUrl(url) })
                .setPositiveButton(R.string.close) { dialog, which ->
                    dialog.cancel()
                }
                .create()

    }

    fun createReverseAlertDialog(context: Context): AlertDialog{
        return AlertDialog.Builder(context)
            .setView(WebView(context).apply { loadUrl("file:///android_asset/Reverse.html") })
            .create()
    }

    fun createSpinnerAlertDialog(context: Context): AlertDialog{
        return AlertDialog.Builder(context)
            .setView(WebView(context).apply { loadUrl("file:///android_asset/Spinner.html") })
            .create()
    }

    fun createByteAlertDialog(context: Context): AlertDialog{
        return AlertDialog.Builder(context)
            .setView(WebView(context).apply { loadUrl("file:///android_asset/XOR.html") })
            .create()
    }
    fun createLoseAlertDialog(context: Context):AlertDialog{
        return AlertDialog.Builder(context)
                .setTitle("Время вышло")
                .setMessage("Вы не выполнили важное поручение")
                .setNegativeButton(R.string.close) { dialog, which ->
                    dialog.cancel()
                }
                .create()
    }

    fun createInsertKeyCaesar(context: Context): AlertDialog{
        val view = LayoutInflater.from(context).inflate(R.layout.ket_caesar, null)
        return AlertDialog.Builder(context)
                .setView(view)
                .setPositiveButton("Потвердить") { dialog, which ->
                    if (view.findViewById<EditText>(R.id.email).text.isNotEmpty()) {
                        User.mutableLiveDataKeyCaesar
                                .postValue(view.findViewById<EditText>(R.id.email).text.toString().toInt())
                    }
                }
                .create()
    }

    fun createInsertKeyViginer(context: Context): AlertDialog{
        val view = LayoutInflater.from(context).inflate(R.layout.key_viginer, null)
        return AlertDialog.Builder(context)
                .setView(view)
                .setPositiveButton("Потвердить"){dialog, which ->
                    if (view.findViewById<EditText>(R.id.key).text.isNotEmpty()) {
                        User.mutableLiveDataKeyViginer
                                .postValue(view.findViewById<EditText>(R.id.key).text.toString())
                    }
                }
                .create()
    }
}