package com.example.mycryptoc

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycryptoc.adapter.CryptosListAdapter
import com.example.mycryptoc.data.DataItem
import com.example.mycryptoc.viewmodel.UserDataViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userDataViewModel: UserDataViewModel
    private val allList = mutableListOf<DataItem>()
    private lateinit var adapter: CryptosListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userDataViewModel = ViewModelProviders.of(this).get(UserDataViewModel::class.java)
        setContentView(R.layout.activity_main)
        swipeRefreshLayout.isRefreshing = true

        adapter = CryptosListAdapter()
        users_rv.adapter = adapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        users_rv.layoutManager = layoutManager

        users_rv.addItemDecoration(DividerItemDecoration(baseContext, layoutManager.orientation))

        userDataViewModel.usersLiveData.observe(this, Observer { data ->
            allList.clear()
            if (!data.data.isNullOrEmpty()) {
                allList.addAll(data.data.toMutableList())
            }
            swipeRefreshLayout.isRefreshing = false
            adapter.run {
                usersList.clear()
                usersList.addAll(allList)
                notifyDataSetChanged()
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            Log.i(TAG, "onCreate: ${swipeRefreshLayout.isRefreshing}")
            if (swipeRefreshLayout.isRefreshing){
                adapter.run {
                    usersList.clear()
                    usersList.addAll(allList)
                    notifyDataSetChanged()
                    Handler(Looper.myLooper()!!).postDelayed({ swipeRefreshLayout.isRefreshing = false },2000L)
                }
            }
        }

        search.doOnTextChanged { text, start, before, count ->
            Log.i(TAG, "doOnTextChanged: ${text}")
//            if (!text.isNullOrEmpty() && text.length > 3) {
//                if (adapter.usersList.isNotEmpty()) {
            adapter.usersList.clear()
            if (text.isNullOrEmpty())
                adapter.usersList.addAll(allList)
            else
                adapter.usersList.addAll(allList.filter {
                    it.name.lowercase().contains(text.toString().lowercase())
                })
            adapter.notifyDataSetChanged()
//                }
//            }
        }

    }

    companion object {
        private const val TAG = "MainActivity"
    }
}