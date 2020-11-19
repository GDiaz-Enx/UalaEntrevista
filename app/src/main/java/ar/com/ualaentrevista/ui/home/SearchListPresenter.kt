package ar.com.ualaentrevista.ui.home

import ar.com.ualaentrevista.base.CustomBasePresenter
import ar.com.ualaentrevista.network.MealsService
import kotlinx.coroutines.launch

class SearchListPresenter() : CustomBasePresenter<SearchListView>() {

    private val service = MealsService()

    fun getRecipes(search: String?) {
        uiScope.launch {
            val response = service.getRecipes(search.orEmpty())
            if (response.isSuccessful) {
                if (!response.body()?.meals.isNullOrEmpty())
                    view?.onRecipesLoadSuccess(response.body()?.meals!!)
                else
                    view?.onEmptyRecipes()
            } else {
                view?.onRecipesLoadError()
            }
        }
    }

    fun getRandom() {
        uiScope.launch {
            val response = service.getRandom()
            if (response.isSuccessful) {
                if (!response.body()?.meals.isNullOrEmpty())
                    view?.onLoadRandomSucess(response.body()?.meals!!.first())
                else
                    view?.onLoadRandomError()
            } else {
                view?.onLoadRandomError()
            }
        }
    }

}