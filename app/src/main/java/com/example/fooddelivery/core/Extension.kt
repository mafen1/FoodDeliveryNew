package com.example.fooddelivery.core

import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar

private val TAG = "TAG"

fun snackBar(view: View, text: String) {
    Snackbar.make(view, text, Snackbar.LENGTH_LONG).show()
}
fun log(text: String){
    Log.d(TAG, text)
}
