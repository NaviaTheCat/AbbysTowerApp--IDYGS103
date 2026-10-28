package com.example.abbystowerapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.abbystowerapp.ui.Screens.LoadScreen
// import com.example.abbystowerapp.ui.Screens.HomeScreen
// import com.example.abbystowerapp.ui.Screens.CharactersScreen

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
                        // quita la pantalla de carga del historial
                        popUpTo(Screen.Load.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            // HomeScreen(
            //     onCharactersClick = { navController.navigate(Screen.Characters.route) }
            // )
        }

        composable(Screen.Characters.route) {
            // CharactersScreen(onBack = { navController.popBackStack() })
        }
    }
}