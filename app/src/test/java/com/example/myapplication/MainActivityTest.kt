package com.example.myapplication

import com.example.myapplication.bean.MobileData
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Before
import org.mockito.Mock


@RunWith(MockitoJUnitRunner::class)
class MainActivityTest {

    @Mock
    private lateinit var activity: MainActivity

    var list=ArrayList<MobileData>()

    @Before
    fun setUp(){
        var dataOne=MobileData(0.2f, "2018-Q1", "1", false)
        var dataTwo=MobileData(0.3f, "2018-Q2", "2", false)
        var dataThree=MobileData(0.4f, "2018-Q3", "3", false)
        var dataFour=MobileData(0.2f, "2018-Q4", "4", false)

        var dataFive=MobileData(0.2f, "2017-Q1", "1", false)
        var dataSix=MobileData(0.22f, "2017-Q2", "2", false)
        var dataSeven=MobileData(0.3f, "2017-Q3", "3", false)
        var dataEight=MobileData(0.4f, "2017-Q4", "4", false)

        var dataNine=MobileData(0.2f, "2007-Q1", "1", false)
        var dataTen=MobileData(0.1f, "2007-Q2", "2", false)
        var dataEle=MobileData(0.3f, "2007-Q3", "3", false)
        var dataTwe=MobileData(0.4f, "2007-Q4", "4", false)

        list.add(dataOne)
        list.add(dataTwo)
        list.add(dataThree)
        list.add(dataFour)
        list.add(dataFive)
        list.add(dataSix)
        list.add(dataSeven)
        list.add(dataEight)
        list.add(dataNine)
        list.add(dataTen)
        list.add(dataEle)
        list.add(dataTwe)

    }

    @Test
    fun testFilterDataOne(){

        assertEquals(activity.filterData(list).size, 2)
    }

    @Test
    fun testFilterDataTwo(){
        assertEquals(activity.filterData(list)[0].volume_of_mobile_data, 1.1f)
    }

    @Test
    fun testFilterDataThree(){
        assertEquals(activity.filterData(list)[0].decreased, true)
    }

    @Test
    fun testFilterDataFour(){
        assertEquals(activity.filterData(list)[1].decreased, false)
    }
}