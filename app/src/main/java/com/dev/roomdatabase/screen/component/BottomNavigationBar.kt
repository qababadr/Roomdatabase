package com.dev.roomdatabase.screen.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dev.roomdatabase.ui.theme.Black3
import com.dev.roomdatabase.utils.Screen

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {

    val bottomNavItems = listOf(
        Screen.HomeScreen,
        Screen.WishlistScreen
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = stringResource(id = item.titleResId)
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.titleResId),
                        style = MaterialTheme.typography.overline
                    )
                },
                selectedContentColor = MaterialTheme.colors.onPrimary,
                unselectedContentColor = Black3,
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }

}
