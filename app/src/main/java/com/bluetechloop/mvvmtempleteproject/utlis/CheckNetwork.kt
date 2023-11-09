package com.bluetechloop.mvvmtempleteproject.utlis

import android.content.Context
import android.net.ConnectivityManager

class CheckNetwork {
      fun isNetworkAvailable(context:Context): Boolean {

        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                return true
                // connected to wifi
            } else return activeNetwork.type == ConnectivityManager.TYPE_MOBILE
        } else {
            // not connected to the internet
            return false
        }

    }

}