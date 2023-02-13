package com.hamza.Foody.ui.HomeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.Foody.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var homeAdapter: HomeAdapter = HomeAdapter()
    private val mealsViewModel by viewModels<MealsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealsViewModel.getMeals()

        lifecycleScope.launch {
            observeToRemoteData()
        }

    }

    private suspend fun observeToRemoteData() {
        mealsViewModel.categories.collect {
            homeAdapter.differ.submitList(it?.categories)
            setUpRecyclerView()
        }
    }

    private fun setUpRecyclerView() {
        binding.mealsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }
    }


}