package com.android.clean.feature.item.domain.repository

import com.android.clean.feature.item.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    
    fun getItems(): Flow<List<Item>>

    suspend fun getItem(id: Int): Item?

    suspend fun insertItem(item: Item)

    suspend fun deleteItem(id: Int)
}
