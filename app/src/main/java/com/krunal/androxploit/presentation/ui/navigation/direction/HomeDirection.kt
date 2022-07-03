package com.krunal.androxploit.presentation.ui.navigation.direction

import androidx.navigation.NamedNavArgument

sealed interface HomeDirection : NavigationCommand {

    object Root : NavigationCommand {
        override val name: String
            get() = "Default"
        override val route: String
            get() = "default_route"
        override val arguments: List<NamedNavArgument>
            get() = emptyList()

    }

    object Home : NavigationCommand {
        override val name: String
            get() = "Home"
        override val route: String
            get() = "home_route"
        override val arguments: List<NamedNavArgument>
            get() = emptyList()

    }

    companion object {
        val Network: NavigationCommand = NetworkDirection.Root
        val Web: NavigationCommand = WebDirection.Root
        val IoT: NavigationCommand = IoTDirection.Root
    }
}