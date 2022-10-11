package com.dev.roomdatabase.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun Modal(
    dialogVisible: Boolean,
    onCloseDialog: () -> Unit,
    dialogContent: @Composable ColumnScope.() -> Unit
) {
    if (dialogVisible) {
        Dialog(onDismissRequest = onCloseDialog) {
            Column(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.large
                    )
            ) {
                dialogContent()
            }
        }
    }

}