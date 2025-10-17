package com.jefferson.week6.soal1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.statusBarsPadding
import com.jefferson.week6.R
import com.jefferson.week6.soal1.ViewModel.FoodDeliveryViewModel

data class Product(val id: Int, val name: String, val price: String, val drawableRes: Int)

@Composable
fun FoodDeliveryScreen(
    viewModel: FoodDeliveryViewModel,
    onBack: () -> Unit
) {
    val sampleProducts = viewModel.sampleProducts

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = PandaGrayBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .statusBarsPadding()
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Find Your\nFavourite Food",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp
                        ),
                        color = Color.Black
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.pandamart_delivery),
                    contentDescription = "Panda Delivery",
                    modifier = Modifier
                        .size(100.dp)
                        .offset(y = (-10).dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(PandaPurple),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Search for Food",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(6.dp),
                colors = CardDefaults.cardColors(containerColor = PandaPurple)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        Text(
                            "Special Deal For\nDecember",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                        ) {
                            Text("Buy Now", color = PandaPurple, fontWeight = FontWeight.Bold)
                        }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.special_deal),
                        contentDescription = "Special Deal",
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Popular Menu", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(
                    "View More",
                    color = PandaPurple,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {}
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(sampleProducts) { product ->
                    Card(
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = product.drawableRes),
                                contentDescription = product.name,
                                modifier = Modifier.size(80.dp),
                                contentScale = ContentScale.Fit
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(product.name, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(product.price, color = PandaPurple)
                        }
                    }
                }
            }
        }
    }
}
