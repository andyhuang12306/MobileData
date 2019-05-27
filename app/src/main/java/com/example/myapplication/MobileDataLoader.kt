package com.example.myapplication

import io.reactivex.Observable
import io.reactivex.functions.Function

class MobileDataLoader :BaseLoader(){

    lateinit var mDataService: DataService

    init{
        mDataService= RetrofitServiceManager.getInstance().create(DataService::class.java)
    }

    fun getMobileData(id: String, limit: Int): Observable<List<MobileData>>{
        return observe(mDataService.getData(id, limit))
    }
}