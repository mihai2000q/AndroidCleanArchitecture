package com.android.clean.common.mapping

import com.android.clean.feature.item.data.model.ItemModel
import com.android.clean.feature.item.domain.model.Item
import java.time.LocalDateTime

fun ItemModel.toItem(): Item {
    return Item(
        id = this.id?.toString() ?: "",
        title = this.title,
        quantity = this.quantity,
        createdAt = LocalDateTime.parse(this.createdAt)
    )
}

fun Item.toItemModel(): ItemModel {
    return ItemModel(
        id = this.id?.toInt(),
        title = this.title,
        quantity = this.quantity,
        createdAt = this.createdAt.toString()
    )
}