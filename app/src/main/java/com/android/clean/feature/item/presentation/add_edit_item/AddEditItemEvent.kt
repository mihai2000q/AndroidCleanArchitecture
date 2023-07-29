package com.android.clean.feature.item.presentation.add_edit_item

sealed class AddEditItemEvent {
    data class EnteredTitle(val value: String): AddEditItemEvent()
    object SaveItem: AddEditItemEvent()
}