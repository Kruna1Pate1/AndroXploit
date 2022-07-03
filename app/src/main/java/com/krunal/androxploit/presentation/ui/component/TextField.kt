package com.krunal.androxploit.presentation.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.krunal.androxploit.domain.model.Host

@Composable
fun HostDetailText(host: Host) {

    Column {
        Text(
            text = host.lable,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onPrimary
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(buildAnnotatedString {
            withStyle(style = MaterialTheme.typography.subtitle2.toSpanStyle(),) {
                append("IP Address: ")
            }
            withStyle(style = MaterialTheme.typography.body2.toSpanStyle()) {
                host.ip?.let { append(it) }
            }
        })

        Text(buildAnnotatedString {
            withStyle(style = MaterialTheme.typography.subtitle2.toSpanStyle()) {
                append("MAC Address: ")
            }
            withStyle(style = MaterialTheme.typography.body2.toSpanStyle()) {
                append(host.mac)
            }
        })

        Text(buildAnnotatedString {
            withStyle(style = MaterialTheme.typography.subtitle2.toSpanStyle()) {
                append("Host Name: ")
            }
            withStyle(style = MaterialTheme.typography.body2.toSpanStyle()) {
                append(host.name)
            }
        })
    }
}