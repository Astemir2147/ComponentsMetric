package com.example.feature_components.domain.utils.servises.firebase_callback

import com.example.core.database.entity.ComponentsEntity

interface CallbackDataFromFirebase {
    fun onDataReceived(componentsFromFirebase : List<ComponentsEntity>)
}