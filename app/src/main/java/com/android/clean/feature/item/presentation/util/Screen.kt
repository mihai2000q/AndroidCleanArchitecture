package com.android.clean.feature.item.presentation.util

sealed class Screen(private val route: String) {
    object SplashScreen: Screen("Splash Screen")
    object ItemsScreen: Screen("Items Screen")
    object AddEditItemScreen: Screen("Add Edit Item Screen")

    operator fun invoke(): String = route
}