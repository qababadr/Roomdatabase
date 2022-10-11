package com.dev.roomdatabase.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.roomdatabase.R
import com.dev.roomdatabase.screen.component.BottomNavigationBar
import com.dev.roomdatabase.screen.home.HomeScreen
import com.dev.roomdatabase.screen.home.HomeViewModel
import com.dev.roomdatabase.screen.user.UserDetailScreen
import com.dev.roomdatabase.screen.user.UserViewModel
import com.dev.roomdatabase.screen.wishlist.WishlistScreen
import com.dev.roomdatabase.screen.wishlist.WishlistViewModel
import com.dev.roomdatabase.ui.theme.RoomDatabaseTheme
import com.dev.roomdatabase.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.isSeeding.value
            }
        }

        setContent {

            val navController = rememberNavController()

            val isActiveUser = mainViewModel.isActiveUser
                .collectAsState(initial = false)

            val context = LocalContext.current

            RoomDatabaseTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    },
                    floatingActionButton = {
                        Button(
                            onClick = { navController.navigate(route = Screen.UserDetailScreen.route) },
                            shape = RoundedCornerShape(size = 50.dp),
                            modifier = Modifier.padding(all = 5.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = if (isActiveUser.value) R.drawable.user_pen_solid else R.drawable.user_plus_solid),
                                contentDescription = "",
                                modifier = Modifier.size(size = 32.dp)
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            val viewModel = hiltViewModel<HomeViewModel>()
                            HomeScreen(
                                viewModel = viewModel,
                                context = context
                            )
                        }

                        composable(route = Screen.UserDetailScreen.route) {
                            val viewModel = hiltViewModel<UserViewModel>()
                            UserDetailScreen(
                                viewModel = viewModel,
                                context = context
                            )
                        }

                        composable(route = Screen.WishlistScreen.route){
                            val viewModel = hiltViewModel<WishlistViewModel>()
                            WishlistScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}