package com.hamza.Foody.ui.FilterCategoriesFragment


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hamza.Foody.databinding.CategoryItemBinding
import com.hamza.domain.entity.Category

class FilterCategoriesAdapter : RecyclerView.Adapter<FilterCategoriesAdapter.ViewHolder>() {

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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterCategoriesAdapter.ViewHolder {
        val view = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FilterCategoriesAdapter.ViewHolder, position: Int) {

        val currentItem = differ.currentList[position]

        holder.binding.categoryNameTextView.text = currentItem.strCategory

        Glide
            .with(holder.itemView.context)
            .load(currentItem.strCategoryThumb)
            .into(holder.binding.categoryImageView)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}