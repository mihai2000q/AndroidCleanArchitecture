package com.android.clean.feature.item.domain.model

import java.time.LocalDateTime

data class Item(
    val id: String?,
    val title: String,
    val quantity: Int,
    val createdAt: LocalDateTime
)

class InvalidItemException(message: String): Exception(message)
