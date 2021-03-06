package com.badschizoids.ciphergame.tools

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.webkit.WebView
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
}