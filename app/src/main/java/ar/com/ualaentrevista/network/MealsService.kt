package ar.com.ualaentrevista.network

class MealsService {

    private val recipeApi = RetrofitInstance.getRetrofitInstance().create(MealsApi::class.java)

    suspend fun getRecipes(searchValue: String) = recipeApi.getRecipes(searchValue)

    suspend fun getRandom() = recipeApi.getRandom()

}