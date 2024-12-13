package com.appsv.healthify.presentation

import DiabetesViewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHostContainer(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Analytics,
        NavigationItem.Heart,
        NavigationItem.Notifications,
        NavigationItem.Profile
    )
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    if (item == NavigationItem.Heart) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = Color.Red,
                            modifier = Modifier.size(36.dp)
                        )
                    } else {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label
                        )
                    }
                },
                selected = false,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavHostContainer(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) { HomeScreen() }
        composable(NavigationItem.Analytics.route) { AnalyticsScreen() }
        composable(NavigationItem.Heart.route) {

        }
        composable(NavigationItem.Notifications.route) { NotificationsScreen() }
        composable(NavigationItem.Profile.route) { ProfileScreen() }
    }
}

@Composable
fun HomeScreen() {
    Text("Home Screen", modifier = Modifier.padding(16.dp))
}

@Composable
fun AnalyticsScreen() {
    Text("Analytics Screen", modifier = Modifier.padding(16.dp))
}



@Composable
fun NotificationsScreen() {
    Text("Notifications Screen", modifier = Modifier.padding(16.dp))
}

@Composable
fun ProfileScreen() {
    Text("Profile Screen", modifier = Modifier.padding(16.dp))
}

sealed class NavigationItem(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val label: String) {
    object Home : NavigationItem("home", Icons.Default.Home, "Home")
    object Analytics : NavigationItem("analytics", Icons.Default.Build, "Analytics")
    object Heart : NavigationItem("heart", Icons.Default.Favorite, "Heart")
    object Notifications : NavigationItem("notifications", Icons.Default.Notifications, "Notifications")
    object Profile : NavigationItem("profile", Icons.Default.Person, "Profile")
}