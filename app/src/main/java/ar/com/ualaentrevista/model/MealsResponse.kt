package ar.com.ualaentrevista.model

import com.google.gson.annotations.SerializedName

data class MealsResponse(
    @SerializedName("meals") val meals: List<Meal>
)
