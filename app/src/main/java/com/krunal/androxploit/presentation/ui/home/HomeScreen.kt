package com.krunal.androxploit.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krunal.androxploit.R
import com.krunal.androxploit.presentation.ui.component.DetailCard
import com.krunal.androxploit.presentation.ui.navigation.direction.HomeDirection

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        DetailCard(
            lable = "Network Scanner",
            discription = "Network scanning, includes open ports, available devices, running hosts, router vulnerability, services information, penetrating the network.",
            imgRes = R.drawable.router,
        ) {
            viewModel.navigationManager.navigate(HomeDirection.Network)
        }

        DetailCard(
            lable = "Web Application",
            discription = "Scan for web app vulnerabilities including the OWASP top 10. Find available CVEs. Generate reports and get advice for enhancing the security of your website.",
            imgRes = R.drawable.web,
        ) {
            viewModel.navigationManager.navigate(HomeDirection.Web)
        }

        DetailCard(
            lable = "Internet of Things (IoT)",
            discription = "Scan IoT using different sources including shodan. Unauthorized access, create Botnet, pawn ADB devices. ICS, webcams, Network infrastructure.",
            imgRes = R.drawable.iot,
        ) {
            viewModel.navigationManager.navigate(HomeDirection.IoT)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(viewModel())
}