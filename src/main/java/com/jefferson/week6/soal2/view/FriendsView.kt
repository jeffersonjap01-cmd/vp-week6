package com.jefferson.week6.soal2.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jefferson.week5.soal1.model.User
import com.jefferson.week6.soal2.ViewModel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendsView(
    viewModel: UserViewModel,
    navController: NavHostController
) {
    val allUsers = viewModel.allUsersList()
    val friends = viewModel.friends.collectAsState().value
    val user = viewModel.user.collectAsState().value

    Scaffold(
        bottomBar = { FitnessBottomNavigation(navController = navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Friends",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Text("🔥 ${user.icons.getOrElse(0) { 0 }}", modifier = Modifier.padding(end = 12.dp))
                    Text("👥 ${user.icons.getOrElse(1) { 0 }}", modifier = Modifier.padding(end = 12.dp))
                    Text("🏋️ ${user.icons.getOrElse(2) { 0 }}")
                }
            }
            Spacer(Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 80.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(allUsers) { candidate ->
                    val isFriend = viewModel.isFriend(candidate)
                    FriendCard(
                        friend = candidate,
                        isFriend = isFriend,
                        onToggle = {
                            if (isFriend) viewModel.removeFriend(candidate)
                            else viewModel.addFriend(candidate)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FriendCard(
    friend: User,
    isFriend: Boolean,
    onToggle: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(Color(0xFFDCEBFF)),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color(0xFFB7D9FF), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Friend",
                        tint = Color.White
                    )
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = friend.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "${friend.age} years old",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
            Button(
                onClick = onToggle,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isFriend) Color(0xFFE53935) else Color(0xFF2979FF)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Text(
                    text = if (isFriend) "Unfriend" else "Add Friend",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
