package com.jefferson.week5.soal1.model

data class Workout(
    val name: String,
    val category: String,
    val icon: Int,
    val calories: Int,
    var added: Boolean = false

)