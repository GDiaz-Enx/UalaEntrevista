package ar.com.ualaentrevista.ui.home

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.ualaentrevista.R
import ar.com.ualaentrevista.base.CustomBaseFragment
import ar.com.ualaentrevista.base.CustomBaseView
import ar.com.ualaentrevista.databinding.SearchListFragmentBinding
import ar.com.ualaentrevista.model.Meal
import ar.com.ualaentrevista.ui.detail.RecipeDetailFragment
import com.squareup.picasso.Picasso

class SearchListFragment : CustomBaseFragment<
        SearchListFragmentBinding,
        SearchListPresenter,
        SearchListView>(), SearchListView, MealClickedListener {

    var mHandler: Handler = Handler()
    private var mHandlerTask: Runnable = object : Runnable {
        override fun run() {
            presenter.getRandom()
            mHandler.postDelayed(this, INTERVAL)
        }
    }

    override val presenter = SearchListPresenter()

    override fun layout() = R.layout.search_list_fragment

    override fun init() {
        presenter.getRecipes(EMPTY_SEARCH)
        startRepeatingTask()
    }

    override fun setListeners() {
        super.setListeners()
        with(binding) {
            searchBox.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(text: Editable?) =
                    presenter.getRecipes(text.toString())

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

                override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int ) { }
            })

            clearSearchBoxButton.setOnClickListener { searchBox.text?.clear() }
        }
    }

    override fun onRecipesLoadSuccess(recipes: List<Meal>) {
        toggleEmptyMessageVisibility(false)
        val llm = LinearLayoutManager(activity)
        llm.orientation = LinearLayoutManager.VERTICAL

        with(binding.recipesRecyclerView) {
            adapter?.notifyDataSetChanged()
            val itemAdapter = MealItemAdapter(recipes, requireContext(), this@SearchListFragment)
            layoutManager = llm
            adapter = itemAdapter
        }
    }

    override fun onEmptyRecipes() {
        toggleEmptyMessageVisibility(true)
    }

    private fun toggleEmptyMessageVisibility(showMessage: Boolean) {
        with(binding) {
            if (showMessage){
                recipesRecyclerView.visibility = View.GONE
                emptyMessage.visibility = View.VISIBLE
            } else {
                recipesRecyclerView.visibility = View.VISIBLE
                emptyMessage.visibility = View.GONE
            }
        }
    }

    private fun startRepeatingTask() {
        mHandlerTask.run()
    }

    //Por si se quiere detener la obtención de recetas random
    private fun stopRepeatingTask() {
        mHandler.removeCallbacks(mHandlerTask)
    }

    override fun onLoadRandomSucess(meal: Meal) {
        with(binding) {
            bannerImage.visibility = View.VISIBLE
            Picasso.with(context)
                .load(meal.strMealThumb)
                .placeholder(R.color.transparent)
                .error(R.color.transparent)
                .into(bannerImage)
        }
    }

    override fun onLoadRandomError() {
        binding.bannerImage.visibility = View.GONE
    }

    override fun mealClicked(meal: Meal) {
        val arguments = Bundle()
        arguments.putSerializable(MEAL_SERIALIZED_KEY, meal)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, RecipeDetailFragment.newInstance(meal))
            .addToBackStack(null)
            .commit()
    }

    override fun onRecipesLoadError() {
        Toast.makeText(requireContext(), R.string.home_search_list_error, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = SearchListFragment()
        const val EMPTY_SEARCH = ""
        const val MEAL_SERIALIZED_KEY = "MEAL_SERIALIZED_KEY"
        const val INTERVAL = (1000 * 30).toLong() //30 segundos
    }
}

interface SearchListView : CustomBaseView {
    fun onRecipesLoadSuccess(recipes: List<Meal>)

    fun onRecipesLoadError()

    fun onEmptyRecipes()

    fun onLoadRandomSucess(meal: Meal)

    fun onLoadRandomError()

}

interface MealClickedListener {

    fun mealClicked(meal: Meal)
}