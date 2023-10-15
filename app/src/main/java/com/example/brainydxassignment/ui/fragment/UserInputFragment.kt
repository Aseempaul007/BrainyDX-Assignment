package com.example.brainydxassignment.ui.fragment

import android.app.DatePickerDialog
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
import com.example.brainydxassignment.databinding.FragmentUserInputBinding
import com.example.brainydxassignment.viewmodel.UserViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

@AndroidEntryPoint
class UserInputFragment : Fragment() {

    private var binding: FragmentUserInputBinding? = null
    private var viewModel: UserViewmodel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserInputBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewmodel::class.java)

        binding!!.button.setOnClickListener {
            viewModel?.userName = binding!!.userName.text.toString()
            viewModel?.userRepo = binding!!.userRepo.text.toString()

            val fm = activity?.supportFragmentManager
            val ft = fm?.beginTransaction()
            ft?.replace(R.id.newsFrame, ListFragment())
            ft?.commit()
        }
        return binding!!.root
    }


}