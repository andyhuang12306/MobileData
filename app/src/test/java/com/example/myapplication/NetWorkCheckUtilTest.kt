package com.example.myapplication

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import com.example.myapplication.network.hasNetwork
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat


@RunWith(MockitoJUnitRunner::class)
class NetWorkCheckUtilTest {
    @Mock
    private lateinit var mockContext: Context
    @Mock
    private lateinit var connectivityManager: ConnectivityManager
    @Mock
    private lateinit var networkInfo: NetworkInfo

    @Test
    fun testHasNetwork(){
        `when` (mockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        `when` (connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
        val hasNetwork = hasNetwork(mockContext)
        assertThat(hasNetwork, equalTo(networkInfo.isConnected))
    }
}