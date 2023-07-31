package com.android.clean.feature.item.presentation.add_edit_item

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.clean.feature.item.domain.model.Item
import com.android.clean.feature.item.domain.use_case.ItemUseCases
import com.android.clean.feature.item.domain.use_case.cases.AddItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditItemViewModel @Inject constructor(
    private val useCases: ItemUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AddEditItemState())
    val state: State<AddEditItemState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("itemId")?.let{ id ->
            if (id.isBlank()) return@let
            viewModelScope.launch {
                useCases.getItem(id)?.also {
                    with(it) {
                        _state.value = state.value.copy(
                            id = id,
                            title = title,
                            quantity = quantity,
                            createdAt = createdAt
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditItemEvent) {
        when (event) {
            is AddEditItemEvent.EnteredTitle -> onTitleChanged(event.value)
            is AddEditItemEvent.SaveItem -> saveItem()
        }
    }

    private fun onTitleChanged(title: String) {
        _state.value = state.value.copy(title = title)
    }

    private fun saveItem() = viewModelScope.launch {
        val item: Item
        with (state.value) {
            item = Item(
                id = id,
                title = title,
                quantity = quantity,
                createdAt = createdAt
            )
        }
        useCases.addItem(item)
        _eventFlow.emit(UiEvent.ItemSaved)
    }

    sealed class UiEvent {
        object ItemSaved: UiEvent()
    }
}