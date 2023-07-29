package com.android.clean.feature.item.domain.use_case.cases

import com.android.clean.feature.item.domain.model.InvalidItemException
import com.android.clean.feature.item.domain.model.Item
import com.android.clean.feature.item.domain.repository.ItemRepository

class AddItem(
    private val repository: ItemRepository
) {
    @Throws(InvalidItemException::class)
    suspend operator fun invoke(item: Item) {
        if (item.title.isBlank()) {
            throw InvalidItemException("The title of the note can't be empty.")
        }

        repository.insertItem(item)
    }
}