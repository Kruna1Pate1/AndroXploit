package com.krunal.androxploit.presentation.ui.navigation

import com.krunal.androxploit.presentation.ui.navigation.direction.NavigationCommand
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {

    var command = MutableStateFlow(NavigationCommand.Default)

    fun navigate(
        direction: NavigationCommand
    ) {
        command.value = direction
    }
}