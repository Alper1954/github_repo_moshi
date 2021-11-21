package com.example.githubrepolist.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepolist.network.GitHubApi
import com.example.githubrepolist.network.GitHubApi.retrofitService
import com.example.githubrepolist.network.GitHubRepo
import kotlinx.coroutines.launch

class RepoModel: ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status


    fun getUserRepositories(user:String) {
        viewModelScope.launch {
            try {
                val listResult = GitHubApi.retrofitService.reposForUser(user)
                _status.value = "Success: ${listResult.size} repositories"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}