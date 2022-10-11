package com.dev.roomdatabase.screen.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dev.roomdatabase.R
import com.dev.roomdatabase.local.model.Wishlist
import com.dev.roomdatabase.ui.theme.Gray400
import com.dev.roomdatabase.ui.theme.RedErrorLight

@Composable
fun WishlistItem(
    item: Wishlist,
    modifier: Modifier = Modifier,
    onDelete: (Long) -> Unit
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction = 0.30f)
                .padding(horizontal = 8.dp)
        ) {
            for ((i, product) in item.products.withIndex()) {
                NetworkImage(
                    url = product.medias.first().url,
                    contentScale = ContentScale.Crop,
                    crossFade = 1000,
                    modifier = Modifier
                        .fillMaxSize(fraction = 1 - 0.1f * i)
                        .align(alignment = Alignment.BottomEnd)
                        .border(width = 2.dp, color = Gray400, shape = MaterialTheme.shapes.medium)
                        .clip(shape = MaterialTheme.shapes.medium),
                    onLoading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    onError = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_circle_info_solid),
                            contentDescription = "",
                            tint = RedErrorLight
                        )
                    }
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.subtitle1
                )
                IconButton(onClick = { onDelete(item.id) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash_solid),
                        contentDescription = "",
                        tint = RedErrorLight
                    )
                }
            }
        }
    }
}
