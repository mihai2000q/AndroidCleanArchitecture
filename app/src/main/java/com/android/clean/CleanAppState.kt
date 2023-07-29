package com.android.clean

import android.content.res.Resources
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

data class CleanAppState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val topAppBarVisibility: MutableState<Boolean>,
    val snackbarHostState: SnackbarHostState,
    val drawerState: DrawerState,
    val scope: CoroutineScope,
    val navController: NavHostController,
    val resources: Resources,
) {
    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}