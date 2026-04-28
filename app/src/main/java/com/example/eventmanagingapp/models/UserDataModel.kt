package com.example.eventmanagingapp.models

// Organizer.kt
@Entity(tableName = "organizers")
data class Organizer(
    @PrimaryKey val id: String = "",
    val name: String = "",
    val email: String = "",
    val contact: String = "",
    val company: String = "",
    val isLoggedIn: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
