package com.example.abbystowerapp.Navigation

/**
 * Project: AbbysTowerApp
 * From: com.example.abbystowerapp.Navigation
 * Created by: riqui
 * On: 27/10/2026
 * All rights reserved: 2026
 */

sealed class Screen(val route: String) {
    object Load : Screen("load")
    object Home : Screen("home")
    object Characters : Screen("characters")
}