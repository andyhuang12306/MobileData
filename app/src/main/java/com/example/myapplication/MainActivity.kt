package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.myapplication.bean.MobileData
import io.reactivex.disposables.CompositeDisposable
import java.util.ArrayList

class MainActivity : AppCompatActivity(), MobileDataAdapter.Listener {

    override fun onItemClick(item: MobileData) {

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
                    { response -> if (response.success) onSuccess(response.result.records) },
                    { error -> onError(error) })
        )

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
