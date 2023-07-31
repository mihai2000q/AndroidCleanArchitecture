package com.android.clean.feature.item.domain.use_case.cases

import com.android.clean.feature.item.domain.model.Item
import com.android.clean.feature.item.domain.repository.ItemRepository

class GetItem(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(id: String): Item? {
        return repository.getItem(id.toInt())
    }
}