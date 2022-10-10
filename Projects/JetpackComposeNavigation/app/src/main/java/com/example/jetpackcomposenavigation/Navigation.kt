package com.example.jetpackcomposenavigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.Button
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route){
        composable(route = Screens.MainScreen.route){
            MainScreen(navController = navController)
        }
        composable(
            route = Screens.NavigateScreen.route + "/{name}/",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "Stranger"
                    nullable = true
                }
            )
        ){  entry ->
            NavigateScreen(name = entry.arguments?.getString("name"))
        }
    }
}

@Composable
fun MainScreen(navController: NavController){
    var text by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        TextField(
            value = text,
            onValueChange = {
            text = it
        },
        modifier = Modifier.fillMaxWidth()
            )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                      navController.navigate(Screens.NavigateScreen.withArgs(text))
            },
        ) {
            Text(text = "To NavigateScreen")
        }
    }
}

@Composable
fun NavigateScreen(name: String?){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "Hello, $name")
    }
}