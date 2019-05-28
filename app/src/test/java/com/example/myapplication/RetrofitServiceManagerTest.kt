package com.example.myapplication

import com.example.myapplication.network.RetrofitServiceManager
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertEquals
import java.io.File


@RunWith(MockitoJUnitRunner::class)
class RetrofitServiceManagerTest {

    @Test
    fun testSetNetworkStatus(){
        RetrofitServiceManager.setNetworkStatus(true)
        assertEquals(RetrofitServiceManager.getNetworkStatus(), true)
    }

    @Test
    fun testSetCachePath(){
        RetrofitServiceManager.setCachPath(File("/data/data/cache"))
        assertEquals(RetrofitServiceManager.getCachPath().absolutePath, "/data/data/cache")
    }
}