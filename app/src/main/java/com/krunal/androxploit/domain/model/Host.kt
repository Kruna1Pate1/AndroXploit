package com.krunal.androxploit.domain.model

import com.krunal.androxploit.R

data class Host(
    var lable: String = "Device",
    var ip: String,
    var mac: String = "xx:xx:xx:xx:xx:xx",
    var name: String = "Unknown",
    var imgRes: Int = R.drawable.linux,
//    val clickEvent: () -> Unit
)
