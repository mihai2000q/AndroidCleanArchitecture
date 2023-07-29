package com.android.clean.feature.item.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.clean.feature.item.data.model.ItemModel

@Database(
    entities = [ItemModel::class],
    version = 1
)
abstract class ItemDatabase : RoomDatabase() {

    abstract val itemDao: ItemDao

    companion object {
        const val NAME = "items_db"
    }
}