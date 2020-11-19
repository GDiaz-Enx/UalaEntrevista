package ar.com.ualaentrevista.ui.home

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
import com.squareup.picasso.Picasso

class SearchListFragment : CustomBaseFragment<
        SearchListFragmentBinding,
        SearchListPresenter,
        SearchListView>(), SearchListView, MealClickedListener {

    override val presenter = SearchListPresenter()

    override fun layout() = R.layout.search_list_fragment

    override fun init() {
        presenter.getRecipes(EMPTY_SEARCH)
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
            val itemAdapter = RecipeItemAdapter(recipes, requireContext(), this@SearchListFragment)
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

    override fun mealClicked(meal: Meal) {
        //TODO: Not implemented yet
    }

    override fun onRecipesLoadError() {
        Toast.makeText(requireContext(), R.string.home_search_list_error, Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = SearchListFragment()
        const val EMPTY_SEARCH = ""
    }
}

interface SearchListView : CustomBaseView {
    fun onRecipesLoadSuccess(recipes: List<Meal>)

    fun onRecipesLoadError()

    fun onEmptyRecipes()

}

interface MealClickedListener {

    fun mealClicked(meal: Meal)
}