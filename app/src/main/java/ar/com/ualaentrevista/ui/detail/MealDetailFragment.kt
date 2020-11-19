package ar.com.ualaentrevista.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import ar.com.ualaentrevista.R
import ar.com.ualaentrevista.base.CustomBaseActivity
import ar.com.ualaentrevista.base.CustomBaseFragment
import ar.com.ualaentrevista.base.CustomBaseView
import ar.com.ualaentrevista.databinding.RecipeDetailFragmentBinding
import ar.com.ualaentrevista.model.Meal
import com.squareup.picasso.Picasso


class RecipeDetailFragment : CustomBaseFragment<
        RecipeDetailFragmentBinding,
        MealDetailPresenter,
        RecipeDetailView>(), RecipeDetailView {

    override val presenter = MealDetailPresenter()

    override fun layout() = R.layout.recipe_detail_fragment

    @SuppressLint("SetJavaScriptEnabled")
    override fun init() {
        showBackButton(true)
        (requireContext() as CustomBaseActivity).setToolbarTitle(getString(R.string.recipe_detail_toolbar_title))

        val meal = arguments!!.getSerializable(MEAL_SERIALIZED_KEY) as Meal
        with(binding) {
            Picasso.with(context)
                .load(meal.strMealThumb)
                .placeholder(R.color.transparent)
                .error(R.color.transparent)
                .into(recipeImage)

            val urlVideo = meal.strYoutube?.replace(
                BASE_YOUTUBE_URL,
                EMBED_YOUTUBE_URL
            )
            recipeVideo.settings.javaScriptEnabled = true
            recipeVideo.loadUrl(urlVideo)

            recipeName.text = meal.strMeal
            recipeInstructions.text = meal.strInstructions

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                Meal.getIngredientsMeasureList(meal)
            )
            ingredientsList.adapter = adapter
        }
    }

    companion object {
        fun newInstance(meal: Meal) = RecipeDetailFragment().apply {
            val bundle = Bundle().apply {
                putSerializable(MEAL_SERIALIZED_KEY, meal)
            }
            arguments = bundle
        }

        private const val MEAL_SERIALIZED_KEY = "MEAL_SERIALIZED_KEY"
        const val BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v="
        const val EMBED_YOUTUBE_URL = "https://www.youtube.com/embed/"
    }
}

interface RecipeDetailView : CustomBaseView
