package com.android.clean.feature.item.domain.use_case.cases

import com.android.clean.feature.item.domain.model.Item
import com.android.clean.feature.item.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class GetItems(
    private val repository: ItemRepository
) {
    operator fun invoke(): Flow<List<Item>> {
        return repository.getItems()
    }
}