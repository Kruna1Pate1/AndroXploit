package com.krunal.androxploit.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.krunal.androxploit.domain.model.Host

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailCard(
    lable: String,
    discription: String,
    imgRes: Int,
    clickEvent: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .height(180.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(5.dp),
        onClick = clickEvent
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp, 8.dp)
        ) {
            Text(
                text = lable,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onPrimary
            )

            Spacer(modifier = Modifier.height(18.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(imgRes),
                    contentDescription = "Network",
                    modifier = Modifier
                        .size(80.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = discription,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HostCard(
    host: Host,
    clickEvent: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(5.dp),
        onClick = clickEvent
    ) {
        Row(
            modifier = Modifier.padding(12.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(host.imgRes),
                contentDescription = host.lable,
                modifier = Modifier
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))

            HostDetailText(host)
        }
    }
}