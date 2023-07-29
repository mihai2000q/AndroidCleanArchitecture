package com.android.clean.feature.item.domain.use_case.cases

import com.android.clean.feature.item.domain.repository.ItemRepository

class DeleteItem(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(id: String) {
        repository.deleteItem(id.toInt())
    }
}