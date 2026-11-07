package com.example.abbystowerapp.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.abbystowerapp.ui.Screens.CharactersScreen
import com.example.abbystowerapp.ui.Screens.DetailScreen
import com.example.abbystowerapp.ui.Screens.HomeScreen
import com.example.abbystowerapp.ui.Screens.LoadScreen
import com.example.abbystowerapp.ui.Screens.HistoryScreen
import com.example.abbystowerapp.ui.Screens.LevelsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Load.route
    ) {
        composable(Screen.Load.route) {
            LoadScreen(
                onFinished = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Load.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                onCharactersClick = { navController.navigate(Screen.Characters.route) },
                onLevelsClick = { navController.navigate(Screen.Levels.route) },
                onHistoryClick = { navController.navigate(Screen.History.route) }
            )
        }

        composable(Screen.Characters.route) {
            CharactersScreen(
                onBack = { navController.popBackStack() },
                onCharacterClick = { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { entry ->
            val id = entry.arguments?.getInt("id") ?: 0
            DetailScreen(
                personajeId = id,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Levels.route) {
            LevelsScreen(onBack = { navController.popBackStack() })
        }
        composable(Screen.History.route) {
            HistoryScreen(onBack = { navController.popBackStack() })
        }

    }
}