package com.makelick.numbermagic.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.makelick.numbermagic.ui.main.MainScreen

@Composable
fun NumberMagicNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = NavDestinations.Main.route
    ) {
        composable(route = NavDestinations.Main.route) {
            MainScreen()
        }
    }
}

sealed class NavDestinations(val route: String) {
    data object Main : NavDestinations("main")

}