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


    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _repos = MutableLiveData<List<GitHubRepo>>()
    val repos: LiveData<List<GitHubRepo>> = _repos


    fun getUserRepositories(user:String) {
        viewModelScope.launch {
            try {
                _repos.value = GitHubApi.retrofitService.reposForUser(user)
                _status.value = "Success repositories retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}