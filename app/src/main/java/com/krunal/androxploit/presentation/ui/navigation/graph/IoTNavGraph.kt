package com.krunal.androxploit.presentation.ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.krunal.androxploit.presentation.ui.commingsoon.ComingSoonScreen
import com.krunal.androxploit.presentation.ui.navigation.direction.HomeDirection
import com.krunal.androxploit.presentation.ui.navigation.direction.IoTDirection

fun NavGraphBuilder.iotNavGraph(navController: NavHostController) {

    navigation(
        route = HomeDirection.IoT.route,
        startDestination = IoTDirection.Temp.route
    ) {
        composable(
            route = IoTDirection.Temp.route,
            arguments = IoTDirection.Temp.arguments
        ) {
            ComingSoonScreen()
        }
    }
}