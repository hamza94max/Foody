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
import com.hamza.Foody.Utils.showIf
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
            getMealsByCategory()
        }


    }


    private suspend fun getMealsByCategory() {
        filterViewModel.meals.collect {
            it?.let { response ->
                Log.i("hamzafilter", "response in fragment/ ${response.meals}")
                meals = response.meals
                binding.notFoundTextView.showIf { meals.isEmpty() }
            }
        }
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

        openSelectedCategory(Integer.valueOf(args.category.idCategory) - 1)
    }

    private fun openSelectedCategory(position: Int) {
        binding.tabLayout.getTabAt(position)?.select()

        setUpRecyclerView(position)

    }

    private fun getNameOfCategory(position: Int): String {
        return categoryList[position].strCategory
    }

    private fun setUpRecyclerView(position: Int) {

        filterViewModel.getMeals(getNameOfCategory(position))

        filterCategoriesAdapter.differ.submitList(meals)
        binding.filterMealsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
            adapter = filterCategoriesAdapter
            Log.d("hamzaF", "Recyclerview is activated")
        }
    }

}