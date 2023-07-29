package com.android.clean.feature.item.presentation.items

import com.android.clean.feature.item.domain.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

data class ItemsState(
    val items: Flow<List<Item>> = MutableSharedFlow()
)