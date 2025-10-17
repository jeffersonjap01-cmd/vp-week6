package com.jefferson.week6.soal1.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jefferson.week6.soal1.model.Product
import com.jefferson.week6.R

class PandamartViewModel : ViewModel() {

        val samplePandamartProducts = listOf(
            Product(1, "Fresh Peach", "$9", R.drawable.peach),
            Product(2, "Avocado", "$7", R.drawable.avocado),
            Product(3, "Pineapple", "$5", R.drawable.pineapple),
            Product(4, "Broccoli", "$3", R.drawable.broccoli)
        )
    }

