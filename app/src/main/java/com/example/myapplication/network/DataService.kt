package com.example.myapplication.network

import com.example.myapplication.bean.Response
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Query

interface DataService{

    @GET("datastore_search")
    fun getData(@Query("resource_id") id: String, @Query("limit") limit: Int): Observable<Response>
}