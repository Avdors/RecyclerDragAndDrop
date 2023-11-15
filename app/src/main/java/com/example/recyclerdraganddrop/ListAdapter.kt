package com.example.recyclerdraganddrop

import android.content.ClipData
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerdraganddrop.databinding.ListRowBinding

class ListAdapter(private var list: List<String>, private val listener: Listener) : RecyclerView.Adapter<ListAdapter.ListViewHolder>(), View.OnTouchListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ListRowBinding.inflate(layoutInflater, parent, false)
        return ListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.text.text = list[position]
        holder.frameLayout.tag = position
        holder.frameLayout.setOnTouchListener(this)
        holder.frameLayout.setOnDragListener(DragListener(listener))
    }

    override fun getItemCount(): Int = list.size

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = View.DragShadowBuilder(v)
            v.startDragAndDrop(data, shadowBuilder, v, 0)
            return true
        }
        return false
    }

    fun getList(): List<String> = list

    fun updateList(list: List<String>) {
        this.list = list
    }

    class ListViewHolder(binding: ListRowBinding) : RecyclerView.ViewHolder(binding.root) {
        val text: TextView = binding.text
        val frameLayout: FrameLayout = binding.frameLayoutItem
    }
}