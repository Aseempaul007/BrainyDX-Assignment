package com.example.brainydxassignment.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brainydxassignment.R
import com.example.brainydxassignment.adapter.UserAdpater
import com.example.brainydxassignment.databinding.FragmentListBinding
import com.example.brainydxassignment.databinding.FragmentUserInputBinding
import com.example.brainydxassignment.viewmodel.UserViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var binding: FragmentListBinding? = null
    private var viewModel: UserViewmodel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewmodel::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val userName = viewModel!!.userName
            val userRepo = viewModel!!.userRepo
            val users = viewModel?.getUsers(userName,userRepo)
            Log.d("MYTAG",users.toString())

            withContext(Dispatchers.Main){
                binding!!.recyclerView.adapter = UserAdpater(requireContext(),users!!)
                binding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        return binding!!.root
    }

}