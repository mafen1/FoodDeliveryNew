package com.example.fooddelivery.data.login.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "login_table",
    indices = [
        Index("email", unique = true)
    ]
)
@Parcelize
data class Login(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(collate = ColumnInfo.NOCASE)
    var email: String,
    var password: String
): Parcelable
