package com.example.brainydxassignment.viewmodel

import androidx.lifecycle.ViewModel
import com.example.brainydxassignment.data.remote.model.ModelClassItem
import com.example.brainydxassignment.data.remote.model.Verification
import com.example.brainydxassignment.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewmodel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    var userName: String = ""
    var userRepo: String = ""

    suspend fun getUsers(
        owner: String,
        repo: String
    ): ArrayList<ModelClassItem> {
        return repository.getUsers(owner, repo)
    }

    suspend fun getUsersByTime(
        owner: String,
        repo: String,
        since: String,
        until: String,
    ): ArrayList<ModelClassItem> {
        return repository.getUsersByTime(owner, repo, since, until)
    }

}