package ar.com.ualaentrevista.network

import ar.com.ualaentrevista.model.MealsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {

    @GET("search.php")
    suspend fun getRecipes(@Query("s") searchValue: String ): Response<MealsResponse>

    //Los métodos con sufijo "Normal" consumen el endpoint sin coroutines
    @GET("search.php")
    fun getRecipesNormal(@Query("s") searchValue: String ): Call<MealsResponse>

}