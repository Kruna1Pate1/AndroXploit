package com.krunal.androxploit.presentation.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.krunal.androxploit.presentation.ui.navigation.direction.HomeDirection

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeDirection.Root.route
    ) {
        homeNavGraph(navController)
    }
}