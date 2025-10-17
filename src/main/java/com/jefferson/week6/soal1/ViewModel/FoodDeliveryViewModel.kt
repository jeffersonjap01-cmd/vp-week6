package com.jefferson.week6.soal1.ViewModel

import androidx.lifecycle.ViewModel
import com.jefferson.week6.R
import com.jefferson.week6.soal1.view.Product

class FoodDeliveryViewModel : ViewModel() {

    // Immutable list of products (you can later make it MutableState if dynamic)
    val sampleProducts = listOf(
        Product(1, "Zinger Burger", "2$", R.drawable.food_burger),
        Product(2, "Roll Paratha", "3$", R.drawable.food_paratha),
        Product(3, "Tomato Soup", "2$", R.drawable.food_soup),
        Product(4, "Long Burger", "5$", R.drawable.food_long_burger),
        Product(5, "Creamy Biscuit", "5$", R.drawable.food_macaron),
        Product(6, "Cupcake", "3$", R.drawable.food_cupcake)
    )
}
