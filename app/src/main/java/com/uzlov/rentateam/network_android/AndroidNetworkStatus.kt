package com.uzlov.rentateam.network_android

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import io.reactivex.rxjava3.subjects.BehaviorSubject


class AndroidNetworkStatus(context: Context) : INetworkStatus {
    private val statusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        statusSubject.onNext(false)
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val request = NetworkRequest.Builder().build()
            connectivityManager.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    statusSubject.onNext(true)
                }

                override fun onUnavailable() {
                    statusSubject.onNext(false)
                }

                override fun onLost(network: Network) {
                    statusSubject.onNext(false)
                }
            })
        } else {
            val activeNetwork: NetworkInfo? = connectivityManager.getActiveNetworkInfo()
            if (activeNetwork != null) {
                // connected to the internet
                when (activeNetwork.type) {
                    ConnectivityManager.TYPE_WIFI  -> {
                        statusSubject.onNext(true)
                    }
                    ConnectivityManager.TYPE_MOBILE -> {
                        statusSubject.onNext(true)
                    }
                    else -> {
                        statusSubject.onNext(true)
                    }
                }
            } else {
                statusSubject.onNext(false)
            }
        }
    }

    override fun isOnline() = statusSubject
    override fun isOnlineSingle() = statusSubject.first(false)
}
