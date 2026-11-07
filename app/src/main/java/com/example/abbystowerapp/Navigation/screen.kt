package com.example.abbystowerapp.Navigation

/**
 * Project: AbbysTowerApp
 * From: com.example.abbystowerapp.Navigation
 * Created by: karla
 * On: 04/11/2026
 * All rights reserved: 2026
 */


sealed class Screen(val route: String) {
    object Load : Screen("load")
    object Home : Screen("home")
    object Characters : Screen("characters")
    object Levels : Screen("levels")
    object History : Screen("history")

    object Detail : Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}