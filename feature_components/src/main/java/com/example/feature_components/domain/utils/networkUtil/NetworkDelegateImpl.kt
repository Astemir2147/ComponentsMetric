package com.example.feature_components.domain.utils.networkUtil

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkDelegateImpl : NetworkDelegate {
    override fun checkNetworkAvailability(context : Context) : Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            val hasCellularConnection =
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            val hasWifiConnection =
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            val hasEthernetConnection =
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            return hasCellularConnection || hasWifiConnection || hasEthernetConnection
        }
        return false
    }
}