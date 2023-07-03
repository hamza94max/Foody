package com.hamza.Foody.ui.HomeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hamza.Foody.databinding.FragmentHomeBinding
import com.hamza.domain.entity.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var homeAdapter: HomeAdapter

    private val categoriesViewModel by viewModels<CategoriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesViewModel.getCategories()

        lifecycleScope.launch { observeToMealsData() }

        handleSearchView()

    }

    private suspend fun observeToMealsData() {
        categoriesViewModel.categories.collect {
            it?.categories?.let { categories -> setUpRecyclerView(categories) }
        }
    }

    private fun setUpRecyclerView(categories: List<Category>) {
        binding.mealsRecyclerView.apply {
            homeAdapter.differ.submitList(categories)
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }
    }

    private fun handleSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val filteredList = homeAdapter.differ.currentList.filter { category ->
                    category.strCategory.contains(newText, ignoreCase = true)
                }
                homeAdapter.differ.submitList(filteredList)

                return true
            }
        })
    }
}
