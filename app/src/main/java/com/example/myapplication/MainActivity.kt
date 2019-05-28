package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageButton
import android.widget.Toast
import com.example.myapplication.bean.MobileData
import io.reactivex.disposables.CompositeDisposable
import java.util.ArrayList

open class MainActivity : AppCompatActivity(), MobileDataAdapter.Listener {

    override fun onItemClick(item: MobileData) {
        Toast.makeText(this, "This year mobile data consumption decreased", Toast.LENGTH_SHORT).show()
    }

    private var recyclerView: RecyclerView? = null
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compositeDisposable = CompositeDisposable()
        initRecyclerView()

        loadData()
    }

    private fun loadData() {
        compositeDisposable?.add(
            MobileDataLoader()
                .getMobileData("a807b7ab-6cad-4aa6-87d0-e283a7353a0f", 56)
                .subscribe(
                    { response -> if (response.success) onSuccess(filterData(response.result.records)) },
                    { error -> onError(error) })
        )

    }

    fun filterData(list: ArrayList<MobileData>): ArrayList<MobileData> {
        var l =
            list.filter { item -> !"2004200520062007".contains(item.quarter.substring(0, 4)) } as ArrayList<MobileData>
        val newList = ArrayList<MobileData>()

        var lastYear=""
        var lastVolume=0f;
        var data=MobileData(0f, "", "", false)
        l.map { item ->
            val currentYear = item.quarter.substring(0, 4)
            if(lastYear==currentYear){
                data.volume_of_mobile_data+=item.volume_of_mobile_data
                if(lastVolume>item.volume_of_mobile_data){
                    data.decreased=true
                }
                lastVolume=item.volume_of_mobile_data
            }else{
                newList.add(data)
                data=item
                lastYear=item.quarter.substring(0,4)
                data.quarter=lastYear
                data._id=newList.size.toString()
                lastVolume=item.volume_of_mobile_data
            }
        }
        newList.add(data)
        newList.removeAt(0)
        return newList
    }

    private fun onSuccess(list: ArrayList<MobileData>) {
        val adapter = MobileDataAdapter(list, this)
        recyclerView?.adapter = adapter
    }

    private fun onError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val manager = LinearLayoutManager(this)
        recyclerView?.layoutManager = manager as RecyclerView.LayoutManager?
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
        compositeDisposable?.dispose()
    }
}
