package com.example.myapplication

import io.reactivex.Observable

class MobileDataLoader :BaseLoader(){

    lateinit var mDataService: DataService

    init{
        mDataService= RetrofitServiceManager.getInstance().create(DataService::class.java)
    }

    fun getMobileData(): Observable<List<MobileData>>{
        return observe(mDataService.getData())
    }
}