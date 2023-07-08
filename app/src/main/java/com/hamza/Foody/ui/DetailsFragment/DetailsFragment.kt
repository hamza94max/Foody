package com.hamza.Foody.ui.DetailsFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hamza.Foody.databinding.FragmentDetailsBinding
import com.hamza.domain.entity.MealDetails
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()

    private val detailsViewModel by viewModels<DetailsViewModel>()

    lateinit var source: String
    lateinit var youtubeLink: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsViewModel.getMealDetails(args.meal.mealId!!)
        setViews()

    }

    private fun setViews() {
        detailsViewModel.mealDetails.observe(viewLifecycleOwner) { response ->
            binding.mealNameTextView.text = args.meal.mealName
            binding.categoryNameTextView.text =
                response?.mealsDetailsList?.get(0)?.strCategory ?: "Null"
            binding.countryNameTextView.text = response?.mealsDetailsList?.get(0)?.strArea ?: "Null"
            binding.instructionsTextView.text =
                response?.mealsDetailsList?.get(0)?.strInstructions ?: "Null"

            response?.mealsDetailsList?.get(0)?.let { it1 -> addIngredientToIngredients(it1) }
            response?.mealsDetailsList?.get(0)?.let { it1 -> addMeasures(it1) }

            source = response?.mealsDetailsList?.get(0)?.strSource.toString()
            youtubeLink = response?.mealsDetailsList?.get(0)?.strYoutube.toString()

        }

        binding.source.setOnClickListener {
            openUrl(source)
        }

        binding.youtube.setOnClickListener {
            openUrl(youtubeLink)
        }

    }

    private fun addIngredientToIngredients(meal: MealDetails) {
        val ingredients = mutableListOf<String>()
        ingredients.add(meal.strIngredient1)
        ingredients.add(meal.strIngredient2)
        ingredients.add(meal.strIngredient3)
        ingredients.add(meal.strIngredient4)
        ingredients.add(meal.strIngredient5)
        ingredients.add(meal.strIngredient6)
        ingredients.add(meal.strIngredient7)
        ingredients.add(meal.strIngredient8)
        ingredients.add(meal.strIngredient9)
        ingredients.add(meal.strIngredient10)
        ingredients.add(meal.strIngredient11)
        ingredients.add(meal.strIngredient12)
        ingredients.add(meal.strIngredient13)
        ingredients.add(meal.strIngredient14)
        ingredients.add(meal.strIngredient15)
        ingredients.add(meal.strIngredient16)
        ingredients.add(meal.strIngredient17)
        ingredients.add(meal.strIngredient18)
        ingredients.add(meal.strIngredient19)
        ingredients.add(meal.strIngredient20)

        getIngredient(ingredients)
    }

    private fun getIngredient(ingredients: MutableList<String>) {
        val ingredientText = StringBuilder()
        for (ingredient: String in ingredients) {
            if (ingredient != " " && ingredient.isNotEmpty() && !ingredient.isNullOrEmpty()) {
                ingredientText.append("\n \u2022$ingredient")
            }
            putIngredient(ingredientText.toString())
        }
    }

    private fun putIngredient(ingredient: String) {
        binding.ingredientTextView.text = ingredient
    }

    private fun addMeasures(meal: MealDetails) {
        val measures = mutableListOf<String>()
        measures.add(meal.strMeasure1)
        measures.add(meal.strMeasure2)
        measures.add(meal.strMeasure3)
        measures.add(meal.strMeasure4)
        measures.add(meal.strMeasure5)
        measures.add(meal.strMeasure6)
        measures.add(meal.strMeasure7)
        measures.add(meal.strMeasure8)
        measures.add(meal.strMeasure9)
        measures.add(meal.strMeasure10)
        measures.add(meal.strMeasure11)
        measures.add(meal.strMeasure12)
        measures.add(meal.strMeasure13)
        measures.add(meal.strMeasure14)
        measures.add(meal.strMeasure15)
        measures.add(meal.strMeasure16)
        measures.add(meal.strMeasure17)
        measures.add(meal.strMeasure18)
        measures.add(meal.strMeasure19)
        measures.add(meal.strMeasure20)

        getMeasures(measures)
    }

    private fun getMeasures(measuresList: MutableList<String>) {
        val measureText = StringBuilder()
        for (measure: String in measuresList) {
            if (measure != " " && measure.isNotEmpty()) {
                measureText.append("\n \u2022$measure")
            }
            putMeasuresText(measureText.toString())
        }
    }

    private fun putMeasuresText(measureText: String) {
        binding.measureTextView.text = measureText
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}