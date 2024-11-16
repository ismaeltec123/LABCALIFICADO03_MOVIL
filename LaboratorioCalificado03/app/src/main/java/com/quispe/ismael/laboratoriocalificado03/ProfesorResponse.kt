package com.quispe.ismael.laboratoriocalificado03

data class ProfesorResponse(
    val teachers: List<Profesor>
)

data class Profesor(
    val name: String,
    val last_name: String,
    val phone: String,
    val email: String,
    val imageUrl: String
)

