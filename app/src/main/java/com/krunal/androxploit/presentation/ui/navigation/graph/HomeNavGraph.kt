package com.krunal.androxploit.presentation.ui.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.krunal.androxploit.presentation.ui.home.HomeScreen
import com.krunal.androxploit.presentation.ui.home.HomeViewModel
import com.krunal.androxploit.presentation.ui.navigation.direction.HomeDirection

fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {

    navigation(
        route = HomeDirection.Root.route,
        startDestination = HomeDirection.Home.route
    ) {

        composable(
            route = HomeDirection.Home.route,
            arguments = HomeDirection.Home.arguments
        ) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(homeViewModel)
        }

        networkNavGraph(navController)

        webNavGraph(navController)

        iotNavGraph(navController)
    }
}