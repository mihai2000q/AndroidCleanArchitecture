package com.android.clean.feature.item.presentation.items

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.clean.feature.item.domain.model.Item
import com.android.clean.feature.item.domain.use_case.ItemUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val useCases: ItemUseCases
) : ViewModel() {

    private val _state = mutableStateOf(ItemsState())
    val state: State<ItemsState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var _recentlyDeletedItem: Item? = null

    init {
        _state.value = state.value.copy(items = useCases.getItems())
    }

    fun onEvent(event: ItemsEvent) {
        when (event) {
            is ItemsEvent.DeleteItem -> deleteItem(event.value)
            is ItemsEvent.RestoreItem -> restoreItem()
            is ItemsEvent.IncreaseQuantity -> changeQuantity(event.value, 1)
            is ItemsEvent.DecreaseQuantity -> changeQuantity(event.value, -1)
        }
    }

    private fun deleteItem(item: Item) = viewModelScope.launch {
        _recentlyDeletedItem = item.copy()
        useCases.deleteItem(item.id ?: "")
        _eventFlow.emit(UiEvent.ItemDeleted)
    }

    private fun restoreItem() = viewModelScope.launch {
        _recentlyDeletedItem?.let {
            useCases.addItem(it)
        }
        _recentlyDeletedItem = null
        _eventFlow.emit(UiEvent.ItemRestored)
    }

    private fun changeQuantity(item: Item, quantity: Int) = viewModelScope.launch {
        val newQuantity = item.quantity + quantity
        if(newQuantity > 0)
            useCases.addItem(item.copy(quantity = newQuantity))
    }

    sealed class UiEvent {
        object ItemDeleted: UiEvent()
        object ItemRestored: UiEvent()
    }
}