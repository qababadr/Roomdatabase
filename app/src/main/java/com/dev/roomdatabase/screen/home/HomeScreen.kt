package com.dev.roomdatabase.screen.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dev.roomdatabase.R
import com.dev.roomdatabase.local.model.Product
import com.dev.roomdatabase.screen.component.Modal
import com.dev.roomdatabase.screen.component.ProductCard
import com.dev.roomdatabase.ui.theme.Green500

@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel,
    context: Context
) {
    val products by viewModel.products
        .collectAsState(initial = emptyList())

    val dialogVisible by viewModel.dialogVisible
        .collectAsState()

    val toastMessage = stringResource(id = R.string.txt_added_to_wishlist)

    Modal(
        dialogVisible = dialogVisible,
        onCloseDialog = { viewModel.hideDialog() }
    ) {
        IconButton(
            onClick = {
                viewModel.addToWishlist { addedToWishlistState ->
                    viewModel.hideDialog()
                    if (addedToWishlistState != -1L)
                        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(all = 8.dp)
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.square_plus_solid),
                    contentDescription = "",
                    tint = Green500,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Text(
                    text = stringResource(id = R.string.lbl_add_to_wishlist),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        items(items = products) { product: Product ->
            ProductCard(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 5.dp),
                product = product,
                onLongPress = { id ->
                    viewModel.setProductId(productId = id)
                    viewModel.showDialog()
                }
            )
        }
    }

}
