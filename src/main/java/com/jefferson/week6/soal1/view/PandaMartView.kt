package com.jefferson.week6.soal1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
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
import com.jefferson.week6.R
import com.jefferson.week6.soal1.ViewModel.PandamartViewModel
import com.jefferson.week6.soal1.model.Product

@Composable
fun PandamartScreen(
    viewModel: PandamartViewModel,
    onBack: () -> Unit
) {
    val products = viewModel.samplePandamartProducts

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PandaGrayBackground)
            .systemBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(PandaPurple)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Text(
                text = "Vegetables",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )

            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Find Your\nFavourite Vegetables",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(PandaPurple),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Search for Vegetables",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Featured products",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "View More",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 12.dp)
            ) {
                items(products) { product: Product ->
                    Card(
                        modifier = Modifier
                            .height(160.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                                modifier = Modifier.size(72.dp),
                                contentScale = ContentScale.Fit
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = product.name,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = product.price,
                                color = PandaPurple,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pandamart_promo),
                            contentDescription = "Promo",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Text(
                                text = "20% off on your\nfirst purchase",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
