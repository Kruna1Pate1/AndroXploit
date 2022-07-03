package com.krunal.androxploit.presentation.ui.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.krunal.androxploit.presentation.ui.commingsoon.ComingSoonScreen
import com.krunal.androxploit.presentation.ui.navigation.direction.HomeDirection
import com.krunal.androxploit.presentation.ui.navigation.direction.NetworkDirection
import com.krunal.androxploit.presentation.ui.network.hostlist.HostListScreen
import com.krunal.androxploit.presentation.ui.network.hostlist.HostListViewModel

fun NavGraphBuilder.networkNavGraph(navController: NavHostController) {

    navigation(
        route = HomeDirection.Network.route,
        startDestination = NetworkDirection.HostList.route
    ) {

        composable(
            route = NetworkDirection.HostList.route,
            arguments = NetworkDirection.HostList.arguments
        ) {
            val hostListViewModel: HostListViewModel = hiltViewModel()
            HostListScreen(hostListViewModel)
        }

        composable(
            route = NetworkDirection.HostDetail.route,
            arguments = NetworkDirection.HostDetail.arguments
        ) {
            ComingSoonScreen()
        }

        composable(
            route = NetworkDirection.HostNmap.route,
            arguments = NetworkDirection.HostNmap.arguments
        ) {
            ComingSoonScreen()
        }

        composable(
            route = NetworkDirection.HostMSF.route,
            arguments = NetworkDirection.HostMSF.arguments
        ) {
            ComingSoonScreen()
        }

        composable(
            route = NetworkDirection.HostDoS.route,
            arguments = NetworkDirection.HostDoS.arguments
        ) {
            ComingSoonScreen()
        }
    }
}