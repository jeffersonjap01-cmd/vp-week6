    package com.jefferson.week6.soal2.route

    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.remember
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.navigation.NavHostController
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.navigation.compose.rememberNavController
    import com.jefferson.week6.soal2.ViewModel.UserViewModel
    import com.jefferson.week6.soal2.ViewModel.WorkoutViewModel
    import com.jefferson.week6.soal2.view.ProfileView
    import com.jefferson.week6.soal2.view.WorkoutsView
    import com.jefferson.week6.soal2.view.FriendsView

    enum class View(val route: String) {
        Profile("profile"),
        Workouts("workouts"),
        Friends("friends")
    }

    @Composable
    fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
        val userViewModel = remember { UserViewModel() }
        val workoutViewModel = remember { WorkoutViewModel(userViewModel) }


        NavHost(
            navController = navController,
            startDestination = View.Profile.route,
            modifier = modifier
        ) {
            composable(View.Profile.route) {
                ProfileView(
                    navController = navController,
                    userViewModel = userViewModel,
                    workoutViewModel = workoutViewModel
                )
            }

            composable(View.Workouts.route) {
                WorkoutsView(
                    navController = navController,
                    viewModel = workoutViewModel,
                    userViewModel = userViewModel
                )
            }

            composable(View.Friends.route) {
                FriendsView(
                    navController = navController,
                    viewModel = userViewModel
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
