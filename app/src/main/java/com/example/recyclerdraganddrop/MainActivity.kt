package com.example.recyclerdraganddrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), Listener {
    private lateinit var rvTop: RecyclerView
    private lateinit var rvBottom: RecyclerView
    private lateinit var tvEmptyListTop: TextView
    private lateinit var tvEmptyListBottom: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTop = findViewById(R.id.rvTop)
        rvBottom = findViewById(R.id.rvBottom)
        tvEmptyListTop = findViewById(R.id.tvEmptyListTop)
        tvEmptyListBottom = findViewById(R.id.tvEmptyListBottom)

        initTopRecyclerView()
        initBottomRecyclerView()

        tvEmptyListTop.visibility = View.GONE
        tvEmptyListBottom.visibility = View.GONE
    }

    override fun setEmptyListTop(visibility: Boolean) {
        tvEmptyListTop.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun setEmptyListBottom(visibility: Boolean) {
        tvEmptyListBottom.visibility = if (visibility) View.VISIBLE else View.GONE
    }
    private fun initTopRecyclerView() {
        rvTop.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val topList = listOf("A", "B")
        val topListAdapter = ListAdapter(topList, this)
        rvTop.adapter = topListAdapter
    }

    private fun initBottomRecyclerView() {
        rvBottom.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val bottomList = listOf("C", "D")
        val bottomListAdapter = ListAdapter(bottomList, this)
        rvBottom.adapter = bottomListAdapter
    }
}