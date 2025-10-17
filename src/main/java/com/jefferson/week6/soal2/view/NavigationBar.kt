package com.jefferson.week6.soal2.view

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jefferson.week6.soal2.route.View

@Composable
fun FitnessBottomNavigation(navController: NavHostController) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 6.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == View.Profile.route,
            onClick = {
                navController.navigate(View.Profile.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Outlined.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
        NavigationBarItem(
            selected = currentRoute == View.Workouts.route,
            onClick = {
                navController.navigate(View.Workouts.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Outlined.FitnessCenter, contentDescription = "Workouts") },
            label = { Text("Workouts") }
        )
        NavigationBarItem(
            selected = currentRoute == View.Friends.route,
            onClick = {
                navController.navigate(View.Friends.route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = { Icon(Icons.Outlined.Group, contentDescription = "Friends") },
            label = { Text("Friends") }
        )
    }
}
