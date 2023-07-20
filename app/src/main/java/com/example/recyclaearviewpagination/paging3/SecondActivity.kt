package com.example.recyclaearviewpagination.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.recyclaearviewpagination.R
import com.example.recyclaearviewpagination.adapter.UserPaging3Adapter
import com.example.recyclaearviewpagination.databinding.ActivitySecondBinding
import com.example.recyclaearviewpagination.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SecondActivity : AppCompatActivity(), CoroutineScope {

    lateinit var userViewModel: UserViewModel
    lateinit var userPAging3Adapter: UserPaging3Adapter
    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userPAging3Adapter = UserPaging3Adapter()
        binding.recyclerView.adapter = userPAging3Adapter

        launch {
            userViewModel.flow.collect {
                userPAging3Adapter.submitData(it)
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}