package com.android.clean.feature.item.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemModel(
    val title: String,
    val quantity: Int,
    val createdAt: String,
    @PrimaryKey val id: Int? = null
)