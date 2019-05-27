package com.example.myapplication

import retrofit2.http.GET
import io.reactivex.Observable

interface DataService{

    @GET("https://data.gov.sg/api/action/datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=56")
    fun getData(): Observable<List<MobileData>>
}