package com.example.jetpackcomposenavigation

import androidx.navigation.NavArgs

sealed class Screens(val route: String){
    object MainScreen : Screens("MainScreen")
    object NavigateScreen : Screens("NavigateScreen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg/")
            }
        }
    }
}
