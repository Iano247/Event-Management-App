package com.example.eventmanagingapp.models

@Entity(tableName = "organizers")
data class Organizer(
    @PrimaryKey val id: String = "",
    val name: String = "",
    val email: String = "",
    val contact: String = "",
    val company: String = "",
    val passwordHash: String = "", // Store hashed password
    val isLoggedIn: Boolean = false,
    val isVerified: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)