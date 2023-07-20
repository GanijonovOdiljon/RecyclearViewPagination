package com.example.recyclaearviewpagination.pegination

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(val layoutMAnager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {
    private val TAG = "PaginationScrollListener"

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutMAnager.childCount  // uesrga korinib turgan itemlar soni
        val totalItemCount = layoutMAnager.itemCount // umumiy kelgan ietmlar soni
        val firstVisibleItemPosition = layoutMAnager.findFirstVisibleItemPosition()

        Log.d(TAG, "onScrolled: visible item count $visibleItemCount")
        Log.d(TAG, "onScrolled: total item count $totalItemCount")
        Log.d(TAG, "onScrolled: firstVisibleItemPosition   $firstVisibleItemPosition")

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }

    abstract fun loadMoreItems()

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean


}