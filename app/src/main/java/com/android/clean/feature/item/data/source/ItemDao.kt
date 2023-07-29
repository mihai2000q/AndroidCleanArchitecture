package com.android.clean.feature.item.data.source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.clean.feature.item.data.model.ItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM ItemModel")
    fun getItems(): Flow<List<ItemModel>>

    @Query("SELECT * FROM ItemModel WHERE id = :id")
    suspend fun getItem(id: Int): ItemModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ItemModel)

    @Delete
    suspend fun deleteItem(item: ItemModel)
}