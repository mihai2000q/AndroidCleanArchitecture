package com.android.clean.feature.item.domain.use_case

import com.android.clean.feature.item.domain.use_case.cases.AddItem
import com.android.clean.feature.item.domain.use_case.cases.DeleteItem
import com.android.clean.feature.item.domain.use_case.cases.GetItem
import com.android.clean.feature.item.domain.use_case.cases.GetItems

data class ItemUseCases(
    val getItems: GetItems,
    val getItem: GetItem,
    val addItem: AddItem,
    val deleteItem: DeleteItem,
)