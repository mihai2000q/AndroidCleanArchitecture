package com.android.clean.feature.item.presentation.add_edit_item

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.clean.CleanAppState
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditItemScreen(
    appState: CleanAppState,
    viewModel: AddEditItemViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = state.title,
            onValueChange = {
                viewModel.onEvent(AddEditItemEvent.EnteredTitle(it))
            },
            label = { Text(text = "Title") }
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = {
            viewModel.onEvent(AddEditItemEvent.SaveItem)
            focusManager.clearFocus()
        }) {
            Text(
                text = "Save",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest {
            performEvent(it, appState, context)
        }
    }
}

private fun performEvent(
    event: AddEditItemViewModel.UiEvent,
    appState: CleanAppState,
    context: Context
) {
    when (event) {
        is AddEditItemViewModel.UiEvent.ItemSaved -> {
           appState.popUp()
            Toast.makeText(context, "Item created", Toast.LENGTH_LONG).show()
        }
    }
}