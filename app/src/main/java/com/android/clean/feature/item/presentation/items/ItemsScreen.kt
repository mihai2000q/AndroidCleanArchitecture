package com.android.clean.feature.item.presentation.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.clean.CleanAppState
import com.android.clean.feature.item.presentation.items.components.ItemComposable
import com.android.clean.feature.item.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsScreen(
    appState: CleanAppState,
    viewModel: ItemsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val items = state.items.collectAsStateWithLifecycle(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    appState.navigate(Screen.AddEditItemScreen())
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Item",
                    tint = Color.White
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPaddingModifier ->
        Column(
            modifier = Modifier.padding(innerPaddingModifier)
        ) {
            LazyColumn {
                items(items.value.sortedByDescending { it.createdAt }) {
                    ItemComposable(
                        item = it,
                        onDelete = { viewModel.onEvent(ItemsEvent.DeleteItem(it)) },
                        increaseQuantity = { viewModel.onEvent(ItemsEvent.IncreaseQuantity(it)) },
                        decreaseQuantity = { viewModel.onEvent(ItemsEvent.DecreaseQuantity(it)) }
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            performEvent(it, viewModel, appState)
        }
    }
}

private fun performEvent(
    event: ItemsViewModel.UiEvent,
    viewModel: ItemsViewModel,
    appState: CleanAppState
) {
    when (event) {
        is ItemsViewModel.UiEvent.ItemDeleted -> {
            appState.scope.launch {
                val snackbarResult = appState.snackbarHostState.showSnackbar(
                    message = "Item has been deleted",
                    actionLabel = "Undo",
                    duration = SnackbarDuration.Long
                )

                when (snackbarResult) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.onEvent(ItemsEvent.RestoreItem)
                    }
                    SnackbarResult.Dismissed -> {}
                }
            }
        }

        is ItemsViewModel.UiEvent.ItemRestored -> {
            appState.scope.launch {
                appState.snackbarHostState.showSnackbar(
                    message = "Item has been restored",
                    actionLabel = "Okay",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }
}