package com.krunal.androxploit.domain.model

data class NetworkDetails(
    val dns1: String,
    val dns2: String,
    val gateway: String,
    var ipAddress: String,
    val leaseDuration: String,
    var netmask: Int = 24,
    val serverAddress: String,
)
