package com.krunal.androxploit.presentation.ui.commingsoon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.krunal.androxploit.R

@Composable
fun ComingSoonScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFEFEFE)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.comming_soon),
            contentDescription = "Coming Soon",
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center
        )
    }
}