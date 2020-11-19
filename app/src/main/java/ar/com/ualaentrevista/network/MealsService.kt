package ar.com.ualaentrevista.network

class MealsService {

    private val recipeApi = RetrofitInstance.getRetrofitInstance().create(MealsApi::class.java)

    suspend fun getRecipes(searchValue: String) = recipeApi.getRecipes(searchValue)

    //Los métodos con sufijo "Normal" consumen el endpoint sin coroutines
    fun getRecipesNormal(searchValue: String) = recipeApi.getRecipesNormal(searchValue)

}