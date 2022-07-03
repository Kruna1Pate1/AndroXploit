package com.krunal.androxploit.presentation.ui.navigation.direction

import androidx.navigation.NamedNavArgument

sealed interface IoTDirection : NavigationCommand {

    object Root : NavigationCommand {
        override val name: String
            get() = "IoT Scanner"

        override val route: String
            get() = "iot_route"

        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }

    object Temp : IoTDirection {
        override val name: String
            get() = "IoT Temp"

        override val route: String
            get() = "iot_temp_route"

        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }
}