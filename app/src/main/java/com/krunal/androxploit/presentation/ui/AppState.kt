package com.krunal.androxploit.presentation.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.krunal.androxploit.presentation.ui.navigation.NavigationManager

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    navigationManager: NavigationManager
) = remember {
    AppState(
        scaffoldState = scaffoldState,
        navController = navController,
        navigationManager = navigationManager
    )
}

class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val navigationManager: NavigationManager
) {
}