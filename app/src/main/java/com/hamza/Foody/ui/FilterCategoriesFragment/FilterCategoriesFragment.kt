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
import com.hamza.Foody.databinding.FragmentFilterCategoriesBinding
import com.hamza.Foody.ui.HomeFragment.MealsViewModel
import com.hamza.domain.entity.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilterCategoriesFragment : Fragment() {

    private lateinit var binding: FragmentFilterCategoriesBinding

    private val args: FilterCategoriesFragmentArgs by navArgs()

    private val mealsViewModel by viewModels<MealsViewModel>()
    private var categoryList: List<Category> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterCategoriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealsViewModel.getMeals()

        lifecycleScope.launch {
            getCategories()
        }

    }
    private suspend fun getCategories() {
        mealsViewModel.categories.collect {
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
            Log.i("filterFrag", category.strCategory)
        }

        openSelectedMeal(Integer.valueOf(args.category.idCategory) - 1)
    }

    private fun openSelectedMeal(position: Int) {
        binding.tabLayout.getTabAt(position)?.select()
    }
}