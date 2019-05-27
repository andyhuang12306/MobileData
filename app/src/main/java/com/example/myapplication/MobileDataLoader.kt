package com.example.myapplication

import com.example.myapplication.bean.Response
import com.example.myapplication.network.BaseLoader
import com.example.myapplication.network.DataService
import com.example.myapplication.network.RetrofitServiceManager
import io.reactivex.Observable

class MobileDataLoader : BaseLoader(){

    lateinit var mDataService: DataService

    init{
        mDataService= RetrofitServiceManager.getInstance().create(DataService::class.java)
    }

    fun getMobileData(id: String, limit: Int): Observable<Response>{
        return observe(mDataService.getData(id, limit))
    }
}