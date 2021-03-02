package com.badschizoids.ciphergame.tools

import android.app.AlertDialog
import android.content.Context
import com.badschizoids.ciphergame.R

class AlertsDialog {
    fun createSussesAlertDialog(context: Context): AlertDialog {
        return AlertDialog.Builder(context)
                .setTitle(context.resources.getString(R.string.title_susses))
                .create()
    }

    fun createViginerAlertDialog(context: Context): AlertDialog{
        return AlertDialog.Builder(context)
                .setTitle(context.resources.getString(R.string.viginer))
                .create()
    }

    fun createCaesarAlertDialog(context: Context): AlertDialog{
        return AlertDialog.Builder(context)
                .setTitle(context.resources.getString(R.string.caesar))
                .create()
    }
}