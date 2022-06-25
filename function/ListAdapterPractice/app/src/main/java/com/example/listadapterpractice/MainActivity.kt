package com.example.listadapterpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadapterpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val dataSet = arrayListOf<Monster>().apply {
        add(Monster("조커1",Race.Human,23, listOf(200,20,100),false))
        add(Monster("조커2",Race.Human,23, listOf(200,20,100),false))
        add(Monster("조커3",Race.Human,23, listOf(200,20,100),false))
        add(Monster("조커4",Race.Human,23, listOf(200,20,100),false))
        add(Monster("조커5",Race.Human,23, listOf(200,20,100),false))
        add(Monster("조커6",Race.Human,23, listOf(200,20,100),false))
        add(Monster("조커7",Race.Human,23, listOf(200,20,100),false))
        add(Monster("조커8",Race.Human,23, listOf(200,20,100),false))
    }

    private val myRecyclerViewAdapter: MyRecyclerViewAdapter by lazy {
        MyRecyclerViewAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = myRecyclerViewAdapter
        }
        myRecyclerViewAdapter.submitList(dataSet)

        val itemTouchHelperCallback = ItemTouchHelper(MyItemTouchHelperCallback(binding.rvList))
        itemTouchHelperCallback.attachToRecyclerView(binding.rvList)

        binding.btnFab.setOnClickListener {
            myRecyclerViewAdapter.submitList(dataSet.shuffled())
        }
    }
}