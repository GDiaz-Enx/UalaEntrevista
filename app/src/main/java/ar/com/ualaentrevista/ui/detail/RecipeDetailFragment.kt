package ar.com.ualaentrevista.ui.detail

import android.os.Bundle
import ar.com.ualaentrevista.R
import ar.com.ualaentrevista.base.CustomBaseActivity
import ar.com.ualaentrevista.base.CustomBaseFragment
import ar.com.ualaentrevista.base.CustomBaseView
import ar.com.ualaentrevista.databinding.RecipeDetailFragmentBinding
import ar.com.ualaentrevista.model.Meal
import com.squareup.picasso.Picasso


class RecipeDetailFragment : CustomBaseFragment<
        RecipeDetailFragmentBinding,
        RecipeDetailPresenter,
        RecipeDetailView>(), RecipeDetailView {

    override val presenter = RecipeDetailPresenter()

    override fun layout() = R.layout.recipe_detail_fragment

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

            recipeName.text = meal.strMeal
            recipeInstructions.text = meal.strInstructions
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
    }
}

interface RecipeDetailView : CustomBaseView
