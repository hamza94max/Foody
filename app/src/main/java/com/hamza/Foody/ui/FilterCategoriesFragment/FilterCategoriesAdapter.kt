package com.hamza.Foody.ui.FilterCategoriesFragment


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hamza.Foody.databinding.MealItemBinding
import com.hamza.domain.entity.Meal
import javax.inject.Inject

class FilterCategoriesAdapter @Inject constructor() :
    RecyclerView.Adapter<FilterCategoriesAdapter.ViewHolder>() {

    private lateinit var navController: NavController

    inner class ViewHolder(val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.mealId == newItem.mealId
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterCategoriesAdapter.ViewHolder {
        val view = MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FilterCategoriesAdapter.ViewHolder, position: Int) {

        val currentItem = differ.currentList[position]

        holder.binding.mealNameTextView.text = currentItem.mealName

        Glide
            .with(holder.itemView.context)
            .load(currentItem.imageOfMeal)
            .into(holder.binding.mealImageView)


        holder.itemView.setOnClickListener {
            navController = Navigation.findNavController(it)
            val action =
                FilterCategoriesFragmentDirections.actionFilterCategoriesFragmentToDetailsFragment(
                    currentItem
                )
            navController.navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}