package com.hamza.Foody.ui.FilterCategoriesFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.hamza.Foody.databinding.FragmentFilterCategoriesBinding
import com.hamza.Foody.ui.HomeFragment.CategoriesViewModel
import com.hamza.domain.entity.Category
import com.hamza.domain.entity.Meal
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FilterCategoriesFragment : Fragment() {

    private lateinit var binding: FragmentFilterCategoriesBinding

    private val args: FilterCategoriesFragmentArgs by navArgs()

    private val categoriesViewModel by viewModels<CategoriesViewModel>()
    private val filterViewModel by viewModels<FilterViewModel>()

    @Inject
    lateinit var filterCategoriesAdapter: FilterCategoriesAdapter

    private var categoryList: List<Category> = emptyList()
    private var meals: List<Meal> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterCategoriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesViewModel.getCategories()

        lifecycleScope.launch {
            getCategories()
        }

        filterViewModel.getMeals(args.category.strCategory)

        setUpRecyclerView()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                openSelectedCategory(tab.position)
                val categoryName = tab.text.toString()
                filterViewModel.getMeals(categoryName)
                setUpRecyclerView()
                Log.i("hamzaF", "pos is " + tab.position)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

    }

    private suspend fun getCategories() {
        categoriesViewModel.categories.collect {
            it?.let { response ->
                categoryList = response.categories
                if (categoryList.isNotEmpty()) {
                    addTabs()
                }
            }
        }
    }

    private fun addTabs() {
        for (category: Category in categoryList) {
            binding.tabLayout.addTab(
                binding.tabLayout.newTab().setText(category.strCategory)
            )
        }
    }

    private fun openSelectedCategory(position: Int) {
        binding.tabLayout.getTabAt(position)?.select()

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        Log.i("hamzaF", "recyclerview is activated ")
        filterViewModel.meals.observe(viewLifecycleOwner) {
            filterCategoriesAdapter.differ.submitList(it?.meals)
        }

        binding.filterMealsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
            adapter = filterCategoriesAdapter

        }
    }

}

