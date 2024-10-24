package com.makelick.numbermagic.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.makelick.numbermagic.ui.main.MainScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.makelick.numbermagic.ui.NavDestinations.Detail.ITEM_ID_ARG
import com.makelick.numbermagic.ui.detail.DetailScreen
import com.makelick.numbermagic.ui.main.MainScreenIntent
import com.makelick.numbermagic.ui.main.MainViewModel

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
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(
                viewModel = viewModel,
                onEvent = {
                    when (it) {
                        is MainScreenIntent.NavigateToDetail ->
                            navHostController.navigate(
                                "${NavDestinations.Detail.route}/${it.itemId}"
                            )
                        else -> viewModel.handleIntent(it)
                    }
                }
            )
        }

        composable(
            route = "${NavDestinations.Detail.route}/{$ITEM_ID_ARG}",
            arguments = NavDestinations.Detail.args
        ) { backStackEntry ->
            val ItemId = backStackEntry.arguments?.getLong(ITEM_ID_ARG) ?: return@composable

            DetailScreen(
            )
        }
    }
}

sealed class NavDestinations(val route: String) {
    data object Main : NavDestinations("main")
    data object Detail : NavDestinations("detail") {
        const val ITEM_ID_ARG = "itemId"
        val args = listOf(
            navArgument(ITEM_ID_ARG) { type = NavType.LongType }
        )
    }
}