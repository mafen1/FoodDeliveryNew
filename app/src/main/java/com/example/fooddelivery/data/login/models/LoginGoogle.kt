package com.example.fooddelivery.data.login.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class LoginGoogle(
    val imageURL: Uri,
    val email: String,
    val name: String

): Parcelable