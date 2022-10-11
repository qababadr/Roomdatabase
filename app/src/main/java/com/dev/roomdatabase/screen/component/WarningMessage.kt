package com.dev.roomdatabase.screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dev.roomdatabase.R

@Composable
fun WarningMessage(
    text: String,
    trailingContent: ( @Composable () -> Unit )? = null
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_circle_info_solid),
            tint = MaterialTheme.colors.onSurface,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = text,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.subtitle2
            )
            trailingContent?.invoke()
        }
    }

}