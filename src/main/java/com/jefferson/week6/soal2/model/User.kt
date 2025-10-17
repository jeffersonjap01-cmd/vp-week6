package com.jefferson.week5.soal1.model

data class User(
    val name: String,
    val age: Int,
    val height: Int,
    val weight: Int,
    val icons: MutableList<Int> = mutableListOf(0,0,0)
)