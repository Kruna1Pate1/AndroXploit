package com.krunal.androxploit.presentation.ui.network.hostlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.androxploit.R
import com.krunal.androxploit.domain.model.Host
import com.krunal.androxploit.domain.usecase.network.hosts.GetHostsUseCase
import com.krunal.androxploit.domain.utils.Status
import com.krunal.androxploit.presentation.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HostListViewModel @Inject constructor(
    val navigationManager: NavigationManager,
    val getHostsUseCase: GetHostsUseCase
) : ViewModel() {

    private val TAG = "HostListViewModel"
    private val _state = mutableStateOf(HostListUiState(status = Status.LOADING))
    val state: State<HostListUiState> = _state

    init {
        getHostList()
    }

    private val hostList: List<Host> = listOf(
        Host(
            lable = "Host Router",
            ip = "192.168.1.1",
            mac = "00:15:5d:60:a4:ec",
            name = "TP-LINK TL-WR543G",
            imgRes = R.drawable.host_router,
        ),

        Host(
            lable = "Linux Server",
            ip = "192.168.1.43",
            mac = "b0:1c:c4:2f:1a:de",
            name = "admin.doma.in",
            imgRes = R.drawable.linux
        ),

        Host(
            lable = "Switch",
            ip = "192.168.1.100",
            mac = "00:14:df:08:b5:df",
            name = "Cisco / 3com switch",
            imgRes = R.drawable.network_switch
        ),

        Host(
            lable = "Android Oreo",
            ip = "192.168.1.123",
            mac = "df:00:c5:1a:04:f1",
            name = "Vivo V5S 1606",
            imgRes = R.drawable.android
        )
    )

    private val _hosts = mutableStateListOf<Host>()
    val hosts = _hosts

    private fun addHost(host: Host) {
        _state.value.hostList.add(host)
    }

    fun removeHost(host: Host) {
        _state.value.hostList.remove(host)
    }

    private fun getHostList() {

        viewModelScope.launch(Dispatchers.IO) {
            getHostsUseCase().collect { host ->
//                _state.value.status = Status.SUCCESS
                if (state.value.status == Status.LOADING) {
                    _state.value = HostListUiState(status = Status.SUCCESS)
                }
                addHost(host)
            }
        }
    }
}