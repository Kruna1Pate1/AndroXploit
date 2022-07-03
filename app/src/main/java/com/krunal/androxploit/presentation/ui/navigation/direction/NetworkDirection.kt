package com.krunal.androxploit.presentation.ui.navigation.direction

import androidx.navigation.NamedNavArgument

sealed interface NetworkDirection : NavigationCommand {

    object Root : NavigationCommand {
        override val name: String
            get() = "Network Scanner"

        override val route: String
            get() = "network_route"

        override val arguments: List<NamedNavArgument>
            get() = emptyList()

    }

    object HostList : NetworkDirection {
        override val name: String
            get() = "Network Device List"

        override val route: String
            get() = "host_list_route"

        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }

    object HostDetail : NetworkDirection {
        override val name: String
            get() = "Host Scan"

        override val route: String
            get() = "host_route"

        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }

    object HostNmap : NetworkDirection {
        override val name: String
            get() = "Host Nmap Scan"

        override val route: String
            get() = "nmap_route"

        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }

    object HostMSF : NetworkDirection {
        override val name: String
            get() = "Host MSF Scan"

        override val route: String
            get() = "msf_route"

        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }

    object HostDoS : NetworkDirection {
        override val name: String
            get() = "Host DoS Attack"

        override val route: String
            get() = "dos_route"

        override val arguments: List<NamedNavArgument>
            get() = emptyList()
    }
}