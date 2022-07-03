package com.krunal.androxploit.presentation.ui.network.hostlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.krunal.androxploit.domain.utils.Status
import com.krunal.androxploit.presentation.ui.component.HostCard

@Composable
fun HostListScreen(
    viewModel: HostListViewModel
) {

    val state by viewModel.state

    when (state.status) {

        Status.LOADING -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    strokeWidth = 5.dp
                )
            }
        }

        Status.SUCCESS -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = state.hostList) { host ->
                    HostCard(host = host) {
                    }
                }
            }
        }

        Status.ERROR -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "[Error]: ${state.error}",
                    style = MaterialTheme.typography.h3,
                    color = MaterialTheme.colors.error
                )
            }
        }
    }
}
