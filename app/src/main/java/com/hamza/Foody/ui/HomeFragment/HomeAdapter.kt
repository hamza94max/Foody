package com.hamza.Foody.ui.HomeFragment


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hamza.Foody.databinding.CategoryItemBinding
import com.hamza.domain.entity.Category
import javax.inject.Inject

class HomeAdapter @Inject constructor() : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private lateinit var navController: NavController

    inner class ViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {

        val currentItem = differ.currentList[position]

        holder.binding.categoryNameTextView.text = currentItem.strCategory

        Glide
            .with(holder.itemView.context)
            .load(currentItem.strCategoryThumb)
            .into(holder.binding.categoryImageView)


        holder.binding.cardView.setOnClickListener {
            navController = Navigation.findNavController(it)
            val action =
                HomeFragmentDirections.actionHomeFragmentToFilterCategoriesFragment(currentItem)
            navController.navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}