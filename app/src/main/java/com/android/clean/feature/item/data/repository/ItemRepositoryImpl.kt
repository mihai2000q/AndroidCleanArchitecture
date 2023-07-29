package com.android.clean.feature.item.data.repository

import com.android.clean.common.mapping.toItem
import com.android.clean.common.mapping.toItemModel
import com.android.clean.feature.item.data.source.ItemDao
import com.android.clean.feature.item.domain.model.Item
import com.android.clean.feature.item.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemRepositoryImpl(
    private val dao: ItemDao
) : ItemRepository {
    
    override fun getItems(): Flow<List<Item>> {
        return dao.getItems().map { items -> items.map { it.toItem() } }
    }

    override suspend fun getItem(id: Int): Item? {
        return dao.getItem(id)?.toItem()
    }

    override suspend fun insertItem(item: Item) {
        dao.insertItem(item.toItemModel())
    }

    override suspend fun deleteItem(id: Int) {
        dao.deleteItem(dao.getItem(id)!!)
    }
}