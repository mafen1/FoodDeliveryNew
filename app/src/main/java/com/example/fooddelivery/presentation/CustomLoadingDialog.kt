package com.example.fooddelivery.presentation

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.fooddelivery.R

class CustomLoadingDialog {

    private var dialog: AlertDialog? = null

    fun startLoadingDialog(context: Context, activity: Activity) {

        val builder = AlertDialog.Builder(context)
        val inflater = activity.layoutInflater

        builder.apply {
            setView(inflater.inflate(R.layout.custom_dialog, null))
            setCancelable(true)
        }
        dialog = builder.create()
        dialog!!.show()

    }

    fun closeDialog() {

        dialog?.dismiss()
        dialog?.cancel()

    }
}