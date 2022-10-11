package com.dev.roomdatabase.utils

import com.dev.roomdatabase.R

sealed class Screen(
    val route: String,
    val titleResId: Int = 0,
    val iconResId: Int = 0
) {

    object HomeScreen :Screen(
        route = "home",
        titleResId = R.string.lbl_home,
        iconResId = R.drawable.house_solid
    )

    object WishlistScreen : Screen(
        route = "wishlist",
        titleResId = R.string.lbl_wishlist,
        iconResId = R.drawable.heart_solid
    )

    object UserDetailScreen: Screen(route = "add_user")

}
