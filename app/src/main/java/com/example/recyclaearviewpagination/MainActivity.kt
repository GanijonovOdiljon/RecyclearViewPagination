package com.example.recyclaearviewpagination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclaearviewpagination.adapter.UserPAginationAdapter
import com.example.recyclaearviewpagination.databinding.ActivityMainBinding
import com.example.recyclaearviewpagination.databinding.LoadItemBinding
import com.example.recyclaearviewpagination.model.UserData
import com.example.recyclaearviewpagination.networking.ApiClient
import com.example.recyclaearviewpagination.networking.ApiService
import com.example.recyclaearviewpagination.pegination.PaginationScrollListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var apiService: ApiService
    lateinit var userpaginationAdapter: UserPAginationAdapter
    private var currentPage = 1
    private var TOTAL_PAGES = 0
    private var isLoadPage = false
    private var isLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        apiService = ApiClient.getRetrofit().create(ApiService::class.java)

        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
        userpaginationAdapter = UserPAginationAdapter()
        binding.recyclerView.adapter = userpaginationAdapter

        loadFirstPage()

        binding.recyclerView.addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                currentPage++
                isLoading = true
                loadNextPage()
            }

            override fun isLastPage(): Boolean {
                return isLoadPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })
    }

    private fun loadNextPage() {
        apiService.getUsers(currentPage)
            .enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    if (response.isSuccessful) {
                        userpaginationAdapter.removeLoadingFooter()
                        isLoading = false

                        userpaginationAdapter.addAll(response.body()?.data ?: emptyList())
                        if (currentPage != TOTAL_PAGES) {
                            userpaginationAdapter.addLoadingFooter()
                        } else {
                            isLoadPage = true
                        }
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {}
            })
    }

    private fun loadFirstPage() {
        apiService.getUsers()
            .enqueue(object : Callback<UserData> {
                override fun onResponse(call: Call<UserData>, response: Response<UserData>) {
                    if (response.isSuccessful) {
                        binding.progressBar.visibility = View.GONE
                        TOTAL_PAGES = response.body()?.total_pages ?: 0
                        userpaginationAdapter.addAll(response.body()?.data ?: emptyList())

                        if (currentPage <= TOTAL_PAGES) {
                            userpaginationAdapter.addLoadingFooter()
                        } else {
                            isLoadPage = true
                        }
                    }
                }

                override fun onFailure(call: Call<UserData>, t: Throwable) {}
            })
    }
}

