package com.jefferson.week6.soal2.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jefferson.week6.soal2.ViewModel.UserViewModel
import com.jefferson.week6.soal2.ViewModel.WorkoutViewModel
import com.jefferson.week6.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutsView(
    viewModel: WorkoutViewModel,
    userViewModel: UserViewModel,
    navController: NavHostController
) {
    val workouts by viewModel.workouts.collectAsState()
    val isFormVisible by viewModel.isFormVisible.collectAsState()

    Scaffold(
        bottomBar = { FitnessBottomNavigation(navController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.showAddForm() },
                containerColor = Color(0xFF2979FF),
                contentColor = Color.White
            ) {
                Icon(Icons.Outlined.Add, contentDescription = "Add Workout")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = "Workouts",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(16.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(workouts) { workout ->
                        Card(
                            colors = CardDefaults.cardColors(Color(0xFFDFF5DF)),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(50.dp)
                                            .background(Color(0xFFB2D8B2), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = workout.icon),
                                            contentDescription = workout.name,
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }
                                    Spacer(Modifier.width(16.dp))
                                    Column {
                                        Text(workout.name, fontWeight = FontWeight.Bold)
                                        Text("${workout.calories} Cals", color = Color.Gray)
                                        Text(workout.category, color = Color.Gray)
                                    }
                                }

                                IconButton(
                                    onClick = {
                                        viewModel.removeWorkout(workout)
                                    },
                                    modifier = Modifier
                                        .size(32.dp)
                                        .background(Color(0xFFE53935), CircleShape)
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = "Remove Workout",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (isFormVisible) {
                AddWorkoutForm(viewModel = viewModel, userViewModel = userViewModel)
            }
        }
    }
}

@Composable
fun AddWorkoutForm(viewModel: WorkoutViewModel, userViewModel: UserViewModel) {
    val name by viewModel.name.collectAsState()
    val type by viewModel.type.collectAsState()
    val calories by viewModel.calories.collectAsState()
    val selectedIcon by viewModel.selectedIcon.collectAsState()

    val icons = listOf(
        R.drawable.fire,
        R.drawable.favorite,
        R.drawable.bolt,
        R.drawable.run
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA000000)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Add Workout",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { viewModel.updateName(it) },
                    placeholder = { Text("e.g. Morning Run") },
                    label = { Text("Workout Title") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = type,
                    onValueChange = { viewModel.updateType(it) },
                    placeholder = { Text("e.g. Cardio, Strength") },
                    label = { Text("Type") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = calories,
                    onValueChange = { viewModel.updateCalories(it) },
                    placeholder = { Text("e.g. 200") },
                    label = { Text("Calories Burned") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))
                Text("Choose Icon", fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    icons.forEach { icon ->
                        val selected = selectedIcon == icon
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(
                                    if (selected) Color(0xFFD0E8D0)
                                    else Color(0xFFF0F0F0)
                                )
                                .clickable { viewModel.selectIcon(icon) },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = icon),
                                contentDescription = null,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = { viewModel.saveWorkout() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Workout")
                }

                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = { viewModel.hideAddForm() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}
