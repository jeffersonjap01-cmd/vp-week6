package com.jefferson.week6.soal1.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jefferson.week6.soal1.ViewModel.FoodDeliveryViewModel
import com.jefferson.week6.soal1.ViewModel.HomeViewModel
import com.jefferson.week6.soal1.view.FoodDeliveryScreen
import com.jefferson.week6.soal1.view.HomeScreen
import com.jefferson.week6.soal1.view.PandamartScreen
import com.jefferson.week6.soal1.ViewModel.PandamartViewModel

enum class Screen(val route: String) {
    Home("home"),
    FoodDelivery("food_delivery"),
    Pandamart("pandamart")
}

@Composable
fun AppNavHost(navController: androidx.navigation.NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            val homeVM: HomeViewModel = viewModel()
            HomeScreen(
                viewModel = homeVM,
                onOpenFoodDelivery = { navController.navigate(Screen.FoodDelivery.route) },
                onOpenPandamart = { navController.navigate(Screen.Pandamart.route) }
            )
        }

        composable(Screen.FoodDelivery.route) {
            val fdVM: FoodDeliveryViewModel = viewModel()
            FoodDeliveryScreen(
                viewModel = fdVM,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Pandamart.route) {
            val pmVM: PandamartViewModel = viewModel()
            PandamartScreen(
                viewModel = pmVM,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppNavHostPreview() {
    val navController = rememberNavController()
    AppNavHost(navController = navController)
}
