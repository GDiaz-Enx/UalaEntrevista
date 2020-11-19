package ar.com.ualaentrevista.network

import ar.com.ualaentrevista.model.MealsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {

    @GET("search.php")
    suspend fun getRecipes(@Query("s") searchValue: String ): Response<MealsResponse>

    @GET("random.php")
    suspend fun getRandom(): Response<MealsResponse>

}