package com.krunal.androxploit.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.krunal.androxploit.presentation.ui.navigation.NavigationManager
import com.krunal.androxploit.presentation.ui.navigation.graph.SetUpNavGraph
import com.krunal.androxploit.presentation.ui.theme.AndroXploitTheme

@Composable
fun MainScreen(navigationManager: NavigationManager) {

    val appState: AppState = rememberAppState(navigationManager = navigationManager)

    AndroXploitTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            appState.navigationManager.command.collectAsState().value.also { command ->
                if (command.route.isNotEmpty()) {
                    Log.d("MainScreen", "MainScreen: ${command.route}")
                    appState.navController.navigate(command.route)
                }
            }

            Scaffold(
                scaffoldState = appState.scaffoldState,
//                topBar = // TODO:
            ) {
                SetUpNavGraph(navController = appState.navController)
            }

        }
    }

}