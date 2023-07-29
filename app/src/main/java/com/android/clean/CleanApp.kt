package com.android.clean

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.clean.common.components.NavigationDrawerComposable
import com.android.clean.common.components.SnackbarHostComposable
import com.android.clean.common.components.TopAppBarComposable
import com.android.clean.feature.item.presentation.add_edit_item.AddEditItemScreen
import com.android.clean.feature.item.presentation.items.ItemsScreen
import com.android.clean.feature.item.presentation.util.Screen
import com.android.clean.feature.splash.presentation.SplashScreen
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CleanApp() {
    val appState = rememberAppState()

    NavigationDrawerComposable(
        drawerState = appState.drawerState,
        gesturesEnabled = true
    ) {
        Scaffold(
            snackbarHost = { SnackbarHostComposable(appState.snackbarHostState) },
            topBar = { TopAppBarComposable(
                title = "Android Clean App",
                appState = appState,
                visible = appState.topAppBarVisibility.value
            ) }
        ) { innerPaddingModifier ->
            NavHost(
                navController = appState.navController,
                startDestination = Screen.SplashScreen(),
                modifier = Modifier.padding(innerPaddingModifier)
            ) {
                cleanGraph(appState)
            }
        }
    }
}

fun NavGraphBuilder.cleanGraph(appState: CleanAppState) {
    composable(Screen.SplashScreen()) {
        SplashScreen(appState = appState)
    }

    composable(Screen.ItemsScreen()) {
        ItemsScreen(appState = appState)
        appState.topAppBarVisibility.value = true
    }

    composable(Screen.AddEditItemScreen()) {
        AddEditItemScreen(appState = appState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberAppState(
    topAppBarVisibility: MutableState<Boolean> = mutableStateOf(false),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    drawerState: DrawerState = drawerState(),
    scope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
    resources: Resources = resources()
) =
    remember(
        topAppBarVisibility,
        snackbarHostState,
        drawerState,
        scope,
        navController,
        resources,
    ) {
        CleanAppState(
            topAppBarVisibility = topAppBarVisibility,
            snackbarHostState = snackbarHostState,
            drawerState = drawerState,
            scope = scope,
            navController = navController,
            resources = resources
        )
    }


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun drawerState(
    initialValue: DrawerValue = DrawerValue.Closed
): DrawerState =
    rememberDrawerState(initialValue = initialValue)

@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}