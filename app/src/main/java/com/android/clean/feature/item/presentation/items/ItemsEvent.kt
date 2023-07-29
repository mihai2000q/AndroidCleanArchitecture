package com.android.clean.feature.item.presentation.items

import com.android.clean.feature.item.domain.model.Item

sealed class ItemsEvent {
    data class IncreaseQuantity(val value: Item): ItemsEvent()
    data class DecreaseQuantity(val value: Item): ItemsEvent()
    data class DeleteItem(val value: Item): ItemsEvent()
    object RestoreItem: ItemsEvent()
}