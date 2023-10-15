package com.example.brainydxassignment.ui.fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
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
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var binding: FragmentListBinding? = null
    private var viewModel: UserViewmodel? = null

    var day = 0
    var month = 0
    var year = 0

    var sinceDate: String? = null
    var untilDate: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewmodel::class.java)

        binding?.progressBar?.visibility = View.VISIBLE

        getData()

        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DATE)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)



        binding?.sinceTime?.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    sinceDate = "$dayOfMonth/${month + 1}/$year"
                    binding?.sinceTime?.text = sinceDate
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        binding?.untilTime?.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    untilDate = "$dayOfMonth/${month + 1}/$year"
                    binding?.untilTime?.text = untilDate
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        binding?.searchButton?.setOnClickListener {
            binding?.progressBar?.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                val userName = viewModel!!.userName
                val userRepo = viewModel!!.userRepo
                val users = viewModel?.getUsersByTime(userName, userRepo, sinceDate!!, untilDate!!)
                Log.d("MYTAGG", users.toString())

                withContext(Dispatchers.Main) {
                    binding!!.recyclerView.adapter = UserAdpater(requireContext(), users!!)
                    binding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    binding?.progressBar?.visibility = View.INVISIBLE
                }
            }
        }

        binding?.clearButton?.setOnClickListener {
            getData()
        }

        return binding!!.root
    }

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            val userName = viewModel!!.userName
            val userRepo = viewModel!!.userRepo
            val users = viewModel?.getUsers(userName, userRepo)
            Log.d("MYTAG", users.toString())

            withContext(Dispatchers.Main) {
                binding!!.recyclerView.adapter = UserAdpater(requireContext(), users!!)
                binding!!.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding?.progressBar?.visibility = View.INVISIBLE
            }
        }
    }


}