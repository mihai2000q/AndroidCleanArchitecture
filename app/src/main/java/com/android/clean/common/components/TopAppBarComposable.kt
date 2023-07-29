package com.android.clean.common.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.android.clean.CleanAppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComposable(
    title: String,
    visible: Boolean,
    appState: CleanAppState
) {
    if (!visible) return

    TopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        title = { Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        ) },
        navigationIcon = {
            MenuIcon(scope = appState.scope, drawerState = appState.drawerState)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuIcon(
    scope: CoroutineScope,
    drawerState: DrawerState,
) {
    IconButton(onClick = {
        scope.launch {
            drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = "Hamburger Menu"
        )
    }
}