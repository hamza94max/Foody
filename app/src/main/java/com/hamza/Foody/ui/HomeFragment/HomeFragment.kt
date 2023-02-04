package com.hamza.Foody.ui.HomeFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.Foody.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private lateinit var homeadapter: HomeAdapter

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
        setUpRecyclerView()

        observeToLiveData()


    }

    private fun observeToLiveData() {
        mealsViewModel.categories.asLiveData().observe(viewLifecycleOwner) {
            if (it != null) {
                homeadapter.differ.submitList(it.categories)
            } else {
                Log.i("response", "categories is null")
            }
        }
        mealsViewModel.getMeals()
    }


    private fun setUpRecyclerView() {
        binding.mealsRecyclerView.apply {
            homeadapter = HomeAdapter()
            layoutManager = LinearLayoutManager(context)
            adapter = homeadapter
        }
    }


}