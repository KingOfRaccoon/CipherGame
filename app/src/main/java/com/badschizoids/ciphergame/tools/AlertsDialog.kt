package com.badschizoids.ciphergame.tools

import android.app.AlertDialog
import android.content.Context
import android.webkit.WebView
import com.badschizoids.ciphergame.R

class AlertsDialog {
    fun createSussesAlertDialog(context: Context): AlertDialog {
        return AlertDialog.Builder(context)
                .setTitle(context.resources.getString(R.string.title_susses))
                .create()
    }

    fun createViginerAlertDialog(context: Context): AlertDialog{
        return AlertDialog.Builder(context)
            .setView(WebView(context).apply { loadUrl("file:///android_asset/Vishiner.html") })
                .create()
    }

    fun createCaesarAlertDialog(context: Context): AlertDialog{
        return AlertDialog.Builder(context)
                .setView(WebView(context).apply { loadUrl("file:///android_asset/Caeser.html") })
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
}