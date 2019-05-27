package com.example.myapplication

import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Query

interface DataService{

    @GET("datastore_search")
    fun getData(@Query("resource_id") id: String, @Query("limit") limit: Int): Observable<List<MobileData>>
}