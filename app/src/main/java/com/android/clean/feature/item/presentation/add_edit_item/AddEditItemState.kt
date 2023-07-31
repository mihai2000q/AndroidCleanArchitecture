package com.android.clean.feature.item.presentation.add_edit_item

import java.time.LocalDateTime

data class AddEditItemState(
    val title: String = "",
    val id: String? = null,
    val quantity: Int = 1,
    val createdAt: LocalDateTime = LocalDateTime.now()
)