package com.example.myapplication

import io.reactivex.Observable

class MobileDataLoader :BaseLoader(){

    lateinit var mDataService: DataService

    init{
        mDataService= RetrofitServiceManager.getInstance().create(DataService::class.java)
    }

    fun getMobileData(id: String, limit: Int): Observable<Response>{
        return observe(mDataService.getData(id, limit))
    }
}