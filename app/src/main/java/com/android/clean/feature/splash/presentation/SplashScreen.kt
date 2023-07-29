package com.android.clean.feature.splash.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.clean.CleanAppState
import com.android.clean.feature.item.presentation.util.Screen
import kotlinx.coroutines.delay

private const val SPLASH_TIMEOUT = 1500L

@Composable
fun SplashScreen(
    appState: CleanAppState
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onPrimary,
            strokeWidth = 7.dp
        )
    }

    LaunchedEffect(key1 = true) {
        delay(SPLASH_TIMEOUT)
        appState.clearAndNavigate(Screen.ItemsScreen())
    }
}