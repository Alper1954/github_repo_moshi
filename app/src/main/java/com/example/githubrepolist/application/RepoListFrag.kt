package com.example.githubrepolist.application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.githubrepolist.ItemAdapter

import com.example.githubrepolist.R
import com.example.githubrepolist.databinding.FragmentRepoListBinding

class RepoListFrag : Fragment() {

    private val viewModel: RepoModel by viewModels()
    private lateinit var binding:FragmentRepoListBinding
    private var user:String? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        arguments?.let{
            user = it.getString("user")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoListBinding.inflate(inflater, container, false)
        viewModel.getUserRepositories(user as String)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemAdapter= ItemAdapter()
        binding.recyclerView.adapter = itemAdapter

        viewModel.repos.observe(viewLifecycleOwner, {
            it?.let {
                itemAdapter.submitList(it)
            }
        })
    }
}