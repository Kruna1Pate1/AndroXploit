package com.krunal.androxploit.presentation.ui.navigation.direction

import androidx.navigation.NamedNavArgument

sealed interface NavigationCommand {
    val name: String
    val route: String
    val arguments: List<NamedNavArgument>


    // Constant keys for accessing argument-value from
    // navBackStack
    companion object {
        val Default: NavigationCommand = Root
    }

    object Root : NavigationCommand {
        override val name: String
            get() = "Default"
        override val route: String
            get() = ""
        override val arguments: List<NamedNavArgument>
            get() = emptyList()

    }
}