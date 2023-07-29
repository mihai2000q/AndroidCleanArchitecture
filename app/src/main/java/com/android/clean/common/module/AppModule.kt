package com.android.clean.common.module

import android.app.Application
import androidx.room.Room
import com.android.clean.feature.item.data.repository.ItemRepositoryImpl
import com.android.clean.feature.item.data.source.ItemDatabase
import com.android.clean.feature.item.domain.repository.ItemRepository
import com.android.clean.feature.item.domain.use_case.ItemUseCases
import com.android.clean.feature.item.domain.use_case.cases.AddItem
import com.android.clean.feature.item.domain.use_case.cases.DeleteItem
import com.android.clean.feature.item.domain.use_case.cases.GetItem
import com.android.clean.feature.item.domain.use_case.cases.GetItems
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemDatabase(app: Application): ItemDatabase {
        return Room.databaseBuilder(
            app,
            ItemDatabase::class.java,
            ItemDatabase.NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideItemRepository(db: ItemDatabase): ItemRepository {
        return ItemRepositoryImpl(db.itemDao)
    }

    @Provides
    @Singleton
    fun provideItemUseCases(repository: ItemRepository): ItemUseCases {
        return ItemUseCases(
            getItems = GetItems(repository),
            getItem = GetItem(repository),
            deleteItem = DeleteItem(repository),
            addItem = AddItem(repository),
        )
    }
}