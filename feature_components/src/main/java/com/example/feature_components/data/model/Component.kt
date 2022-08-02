package com.example.feature_components.data.model

data class Component(
    val contractId: Long,
    val componentName: String,
    val personWhichAccept: String,
    val countOfItem: String,
    val dateOfAccept: String,
    val statusOfComponent: String
)