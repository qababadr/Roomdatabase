package com.dev.roomdatabase.screen.component

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.dev.roomdatabase.local.model.Product
import com.dev.roomdatabase.ui.theme.RedErrorLight
import com.dev.roomdatabase.R
import com.dev.roomdatabase.ui.theme.Green500

@Composable
fun ProductCard(
    product: Product,
    onLongPress: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.large,
        elevation = 8.dp,
        modifier = modifier
            .requiredHeight(height = 150.dp)
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onLongPress(product.id) }
                )
            }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            NetworkImage(
                url = product.medias.first().url,
                contentScale = ContentScale.Crop,
                crossFade = 1000,
                modifier = Modifier.fillMaxWidth(fraction = 0.3f),
                onLoading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                },
                onError = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_circle_info_solid),
                            contentDescription = "",
                            tint = RedErrorLight
                        )
                    }
                }
            )
            Column {
                Text(
                    text = product.title,
                    modifier = Modifier
                        .padding(5.dp),
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(3.dp))
                Text(
                    text = product.description,
                    modifier = Modifier
                        .padding(all = 5.dp)
                        .fillMaxWidth(fraction = 0.85f),
                    style = MaterialTheme.typography.subtitle2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(text = stringResource(id = R.string.lbl_price))
                        }
                        append(text = ": $${String.format("%.2f", product.price)}")
                    },
                    color = Green500,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}
