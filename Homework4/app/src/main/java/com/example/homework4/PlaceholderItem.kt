package com.example.homework4

import android.graphics.drawable.Drawable

data class PlaceholderItem(
    val title: String,
    val subtitle: String?,
    val image: Drawable?,
    val type: Int,
    val color: Boolean = false
)
