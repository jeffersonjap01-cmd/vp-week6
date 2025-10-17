package com.jefferson.week6.soal2.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jefferson.week6.soal2.ViewModel.UserViewModel
import com.jefferson.week6.soal2.ViewModel.WorkoutViewModel

@Composable
fun ProfileView(
    userViewModel: UserViewModel,
    workoutViewModel: WorkoutViewModel,
    navController: NavHostController
) {
    val user by userViewModel.user.collectAsState()
    val friends by userViewModel.friends.collectAsState()
    val workouts by workoutViewModel.workouts.collectAsState()

    Scaffold(
        bottomBar = { FitnessBottomNavigation(navController = navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Profile", style = MaterialTheme.typography.headlineSmall)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(Color(0xFFDCEBFF)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    Modifier.padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(Color.LightGray, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile",
                                tint = Color.White
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text("${user.name}, ${user.age}", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                            Text(
                                "${user.height} cm / ${user.weight} kg",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Row {
                                Text("🔥 ${user.icons[0]}  ", fontSize = 12.sp)
                                Text("👥 ${user.icons[1]}  ", fontSize = 12.sp)
                                Text("🏋️ ${user.icons[2]}", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            Text("Recently Added", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 18.sp)
            if (friends.isEmpty()) {
                Text("No friends added yet", fontSize = 14.sp, color = Color.Gray)
            } else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(vertical = 8.dp)
                ) {
                    friends.take(3).forEach { friend ->
                        Card(
                            colors = CardDefaults.cardColors(Color(0xFFDCEBFF)),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.width(120.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .background(Color.LightGray, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "Friend",
                                        tint = Color.White
                                    )
                                }
                                Spacer(Modifier.height(8.dp))
                                Text(friend.name, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                                Text("${friend.age} years old", fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                    }
                }
            }

            Text("Recent Workouts", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 18.sp)
            if (workouts.isEmpty()) {
                Text("No workouts added yet", fontSize = 14.sp, color = Color.Gray)
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    workouts.take(3).forEach { workout ->
                        Card(
                            colors = CardDefaults.cardColors(Color(0xFFDFF5DF)),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(Color(0xFFB2D8B2), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = workout.icon),
                                        contentDescription = "Workout Icon",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                Spacer(Modifier.width(12.dp))
                                Column {
                                    Text(workout.name, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                                    Text("${workout.calories} Cals", fontSize = 12.sp, color = Color.Gray)
                                    Text(workout.category, fontSize = 12.sp, color = Color.Gray)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
