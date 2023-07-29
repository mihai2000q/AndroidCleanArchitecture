package com.android.clean.common.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerComposable(
    drawerState: DrawerState,
    gesturesEnabled: Boolean,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { },
        gesturesEnabled = gesturesEnabled,
        content = content
    )
}