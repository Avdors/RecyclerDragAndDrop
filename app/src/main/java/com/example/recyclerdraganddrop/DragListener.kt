package com.example.recyclerdraganddrop

import android.util.Log
import android.view.DragEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DragListener(private val listener: Listener) : View.OnDragListener {

    private var isDropped = false

    override fun onDrag(v: View, event: DragEvent): Boolean {
        when (event.action) {
           // DragEvent.ACTION_DROP,
           // DragEvent.ACTION_DRAG_EXITED
                    DragEvent.ACTION_DROP -> {
                Log.d("MyLog", "ACTION_DROP")
                isDropped = true
                var positionTarget: Int

                val viewSource = event.localState as? View
                val viewId = v.id
                val flItem = R.id.frame_layout_item
                val tvEmptyListTop = R.id.tvEmptyListTop
                val tvEmptyListBottom = R.id.tvEmptyListBottom
                val rvTop = R.id.rvTop
                val rvBottom = R.id.rvBottom
                Log.d("MyLog", "viewId $viewId")
                when (viewId) {
                    flItem, tvEmptyListTop, tvEmptyListBottom, rvTop, rvBottom -> {
                        Log.d("MyLog", "flItem, tvEmptyListTop, tvEmptyListBottom, rvTop, rvBottom")
                        val target: RecyclerView = when (viewId) {
                            tvEmptyListTop, rvTop -> v.rootView.findViewById(rvTop)
                            tvEmptyListBottom, rvBottom -> v.rootView.findViewById(rvBottom)
                            else -> v.parent as RecyclerView
                        }

                        positionTarget = v.tag as Int
                        Log.d("MyLog", "positionTarget = $positionTarget")

                        Log.d("MyLog", "viewSource $viewSource")
                        if (viewSource != null) {
                            val source = viewSource.parent as RecyclerView
                            val adapterSource = source.adapter as ListAdapter
                            val positionSource = viewSource.tag as Int
                            Log.d("MyLog", "positionSource $positionSource")
                            val list = adapterSource.getList()[positionSource]
                            val listSource = adapterSource.getList().toMutableList()

                            listSource.removeAt(positionSource)
                            adapterSource.updateList(listSource)
                            adapterSource.notifyDataSetChanged()

                            val adapterTarget = target.adapter as ListAdapter
                            Log.d("MyLog", "target $target")
                            val customListTarget = adapterTarget.getList().toMutableList()
                            Log.d("MyLog", "customListTarget $customListTarget")
                            if (positionTarget < 0) {
                                customListTarget.add(list)
                              //  customListTarget.add(positionTarget, list)
                            } else {
                                customListTarget.add(positionTarget, list)
                            }
                            adapterTarget?.updateList(customListTarget)
                            adapterTarget?.notifyDataSetChanged()
                        }
                    }
                }
            }

            DragEvent.ACTION_DRAG_EXITED -> {
                Log.d("MyLog", "Enter ACTION_DRAG_EXITED")
            }
        }

        if (!isDropped && event.localState != null) {
            (event.localState as? View)?.visibility = View.VISIBLE
        }
        return true
    }
}
