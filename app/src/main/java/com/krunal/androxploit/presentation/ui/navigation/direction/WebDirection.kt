package com.krunal.androxploit.presentation.ui.navigation.direction

import androidx.navigation.NamedNavArgument

sealed interface WebDirection : NavigationCommand {

    object Root : NavigationCommand {
        override val name: String
            get() = "Website Scanner"

        override val route: String
            get() = "web_route"

        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }

    object Temp : WebDirection {
        override val name: String
            get() = "Web Temp"

        override val route: String
            get() = "web_temp_route"

        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }
}