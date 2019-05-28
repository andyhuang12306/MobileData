package com.example.myapplication

import android.app.Application
import com.example.myapplication.network.RetrofitServiceManager
import com.example.myapplication.network.hasNetwork

class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        RetrofitServiceManager.setCachPath(this.cacheDir)
        RetrofitServiceManager.setNetworkStatus(hasNetwork(this))
    }

}