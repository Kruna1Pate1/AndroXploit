package com.krunal.androxploit.presentation.ui.network.hostlist

import com.krunal.androxploit.domain.model.Host
import com.krunal.androxploit.domain.utils.Status

data class HostListUiState(
    var status: Status,
    var hostList: MutableList<Host> = mutableListOf(),
    var error: String = ""
)
