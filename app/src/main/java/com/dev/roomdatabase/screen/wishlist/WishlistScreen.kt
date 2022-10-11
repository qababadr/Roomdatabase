package com.dev.roomdatabase.screen.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dev.roomdatabase.R
import com.dev.roomdatabase.screen.component.WarningMessage
import com.dev.roomdatabase.screen.component.WishlistItem

@Composable
fun WishlistScreen(
    viewModel: WishlistViewModel
) {

    val wishlist by viewModel.wishlist
        .collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.lbl_wishlist),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h3
        )
        Spacer(modifier = Modifier.height(height = 20.dp))
        if (wishlist.isEmpty()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)) {
                WarningMessage(text = stringResource(id = R.string.txt_empty_wishlist))
            }
        } else {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                items(items = wishlist) { item ->
                    WishlistItem(
                        item = item,
                        modifier = Modifier.requiredHeight(height = 200.dp),
                        onDelete = { viewModel.deleteWishlist(id = it) }
                    )
                    Divider(modifier = Modifier.fillMaxWidth())
                }

            }
        }
    }
}
