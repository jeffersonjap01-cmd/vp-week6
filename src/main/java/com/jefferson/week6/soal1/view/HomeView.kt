package com.jefferson.week6.soal1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.statusBarsPadding
import com.jefferson.week6.R
import com.jefferson.week6.soal1.ViewModel.HomeViewModel

val PandaPurple = Color(0xFFB04480)
val PandaLightPurple = Color(0xFFDA89B8)
val PandaGrayBackground = Color(0xFFF2F2F2)
val White = Color(0xFFFFFFFF)
val TextBlack = Color(0xFF222222)

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onOpenFoodDelivery: () -> Unit,
    onOpenPandamart: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = PandaGrayBackground) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .statusBarsPadding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(PandaPurple),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pandamart_logo),
                        contentDescription = "Panda",
                        modifier = Modifier.size(28.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Taste the world\nat your Door\nStep!",
                fontWeight = FontWeight.SemiBold,
                color = TextBlack,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(PandaPurple),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "What are you craving?",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(PandaPurple)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Restaurants", color = Color.White, fontWeight = FontWeight.Medium)
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(PandaLightPurple)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Deals", color = Color.White, fontWeight = FontWeight.Medium)
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(PandaLightPurple)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text("Track Order", color = Color.White, fontWeight = FontWeight.Medium)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(180.dp)
                        .clickable { onOpenFoodDelivery() },
                    shape = RoundedCornerShape(18.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Food delivery",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextBlack
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Delivery from \$0.99",
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.food_burger),
                            contentDescription = "Food delivery",
                            modifier = Modifier
                                .size(110.dp)
                                .align(Alignment.CenterHorizontally),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(180.dp)
                        .clickable { onOpenPandamart() },
                    shape = RoundedCornerShape(18.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Pandamart",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = TextBlack
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "New users \$10 off",
                                fontSize = 13.sp,
                                color = Color.Gray
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.group_21),
                            contentDescription = "Pandamart",
                            modifier = Modifier
                                .size(110.dp)
                                .align(Alignment.CenterHorizontally),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Restaurant Available Now!",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = TextBlack
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.promo),
                    contentDescription = "Promo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
