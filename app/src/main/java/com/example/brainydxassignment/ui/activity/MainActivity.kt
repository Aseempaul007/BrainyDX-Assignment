package com.example.brainydxassignment.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brainydxassignment.R
import com.example.brainydxassignment.adapter.UserAdpater
import com.example.brainydxassignment.databinding.ActivityMainBinding
import com.example.brainydxassignment.ui.fragment.UserInputFragment
import com.example.brainydxassignment.viewmodel.UserViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewmodel: UserViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this)[UserViewmodel::class.java]

        goToFragment(supportFragmentManager, UserInputFragment())

    }
    fun goToFragment(fm: FragmentManager, fragment: Fragment) {
        val ft = fm.beginTransaction()
        ft.replace(R.id.newsFrame, fragment)
        ft.commit()
    }
}