package com.krunal.androxploit.presentation.ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.krunal.androxploit.presentation.ui.commingsoon.ComingSoonScreen
import com.krunal.androxploit.presentation.ui.navigation.direction.HomeDirection
import com.krunal.androxploit.presentation.ui.navigation.direction.WebDirection

fun NavGraphBuilder.webNavGraph(navController: NavHostController) {

    navigation(
        route = HomeDirection.Web.route,
        startDestination = WebDirection.Temp.route
    ) {
        composable(
            route = WebDirection.Temp.route,
            arguments = WebDirection.Temp.arguments
        ) {
            ComingSoonScreen()
        }
    }
}