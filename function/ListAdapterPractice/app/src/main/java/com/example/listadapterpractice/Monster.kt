package com.example.listadapterpractice

data class Monster(
    val name: String,
    var race: Race,
    val level: Int,
    val status: List<Int>,
    val encount: Boolean
)

enum class Race {
    Zombie,Human,Goblin,Dragon
}
