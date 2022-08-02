package com.example.feature_components.domain.utils.networkUtil

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

interface NetworkDelegate {
    fun checkNetworkAvailability(context : Context) : Boolean
}