package ar.com.ualaentrevista.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Meal (
    @SerializedName("idMeal") val idMeal : Int,
    @SerializedName("strMeal") val strMeal : String?,
    @SerializedName("strDrinkAlternate") val strDrinkAlternate : String?,
    @SerializedName("strCategory") val strCategory : String?,
    @SerializedName("strArea") val strArea : String?,
    @SerializedName("strInstructions") val strInstructions : String?,
    @SerializedName("strMealThumb") val strMealThumb : String?,
    @SerializedName("strTags") val strTags : String?,
    @SerializedName("strYoutube") val strYoutube : String?,
    @SerializedName("strIngredient1") val strIngredient1 : String?,
    @SerializedName("strIngredient2") val strIngredient2 : String?,
    @SerializedName("strIngredient3") val strIngredient3 : String?,
    @SerializedName("strIngredient4") val strIngredient4 : String?,
    @SerializedName("strIngredient5") val strIngredient5 : String?,
    @SerializedName("strIngredient6") val strIngredient6 : String?,
    @SerializedName("strIngredient7") val strIngredient7 : String?,
    @SerializedName("strIngredient8") val strIngredient8 : String?,
    @SerializedName("strIngredient9") val strIngredient9 : String?,
    @SerializedName("strIngredient10") val strIngredient10 : String?,
    @SerializedName("strIngredient11") val strIngredient11 : String?,
    @SerializedName("strIngredient12") val strIngredient12 : String?,
    @SerializedName("strIngredient13") val strIngredient13 : String?,
    @SerializedName("strIngredient14") val strIngredient14 : String?,
    @SerializedName("strIngredient15") val strIngredient15 : String?,
    @SerializedName("strIngredient16") val strIngredient16 : String?,
    @SerializedName("strIngredient17") val strIngredient17 : String?,
    @SerializedName("strIngredient18") val strIngredient18 : String?,
    @SerializedName("strIngredient19") val strIngredient19 : String?,
    @SerializedName("strIngredient20") val strIngredient20 : String?,
    @SerializedName("strMeasure1") val strMeasure1 : String?,
    @SerializedName("strMeasure2") val strMeasure2 : String?,
    @SerializedName("strMeasure3") val strMeasure3 : String?,
    @SerializedName("strMeasure4") val strMeasure4 : String?,
    @SerializedName("strMeasure5") val strMeasure5 : String?,
    @SerializedName("strMeasure6") val strMeasure6 : String?,
    @SerializedName("strMeasure7") val strMeasure7 : String?,
    @SerializedName("strMeasure8") val strMeasure8 : String?,
    @SerializedName("strMeasure9") val strMeasure9 : String?,
    @SerializedName("strMeasure10") val strMeasure10 : String?,
    @SerializedName("strMeasure11") val strMeasure11 : String?,
    @SerializedName("strMeasure12") val strMeasure12 : String?,
    @SerializedName("strMeasure13") val strMeasure13 : String?,
    @SerializedName("strMeasure14") val strMeasure14 : String?,
    @SerializedName("strMeasure15") val strMeasure15 : String?,
    @SerializedName("strMeasure16") val strMeasure16 : String?,
    @SerializedName("strMeasure17") val strMeasure17 : String?,
    @SerializedName("strMeasure18") val strMeasure18 : String?,
    @SerializedName("strMeasure19") val strMeasure19 : String?,
    @SerializedName("strMeasure20") val strMeasure20 : String?,
    @SerializedName("strSource") val strSource : String?,
    @SerializedName("dateModified") val dateModified : String?
) : Serializable {
    companion object {
        fun getIngredientsMeasureList(meal: Meal) : List<String> {
            var ingredients: ArrayList<String> = arrayListOf()
            if (!meal.strIngredient1.isNullOrBlank()) ingredients.add(meal.strMeasure1.plus(" ").plus(meal.strIngredient1))
            if (!meal.strIngredient2.isNullOrBlank()) ingredients.add(meal.strMeasure2.plus(" ").plus(meal.strIngredient2))
            if (!meal.strIngredient3.isNullOrBlank()) ingredients.add(meal.strMeasure3.plus(" ").plus(meal.strIngredient3))
            if (!meal.strIngredient4.isNullOrBlank()) ingredients.add(meal.strMeasure4.plus(" ").plus(meal.strIngredient4))
            if (!meal.strIngredient5.isNullOrBlank()) ingredients.add(meal.strMeasure5.plus(" ").plus(meal.strIngredient5))
            if (!meal.strIngredient6.isNullOrBlank()) ingredients.add(meal.strMeasure6.plus(" ").plus(meal.strIngredient6))
            if (!meal.strIngredient7.isNullOrBlank()) ingredients.add(meal.strMeasure7.plus(" ").plus(meal.strIngredient7))
            if (!meal.strIngredient8.isNullOrBlank()) ingredients.add(meal.strMeasure8.plus(" ").plus(meal.strIngredient8))
            if (!meal.strIngredient9.isNullOrBlank()) ingredients.add(meal.strMeasure9.plus(" ").plus(meal.strIngredient9))
            if (!meal.strIngredient10.isNullOrBlank()) ingredients.add(meal.strMeasure10.plus(" ").plus(meal.strIngredient10))
            if (!meal.strIngredient11.isNullOrBlank()) ingredients.add(meal.strMeasure11.plus(" ").plus(meal.strIngredient11))
            if (!meal.strIngredient12.isNullOrBlank()) ingredients.add(meal.strMeasure12.plus(" ").plus(meal.strIngredient12))
            if (!meal.strIngredient13.isNullOrBlank()) ingredients.add(meal.strMeasure13.plus(" ").plus(meal.strIngredient13))
            if (!meal.strIngredient14.isNullOrBlank()) ingredients.add(meal.strMeasure14.plus(" ").plus(meal.strIngredient14))
            if (!meal.strIngredient15.isNullOrBlank()) ingredients.add(meal.strMeasure15.plus(" ").plus(meal.strIngredient15))
            if (!meal.strIngredient16.isNullOrBlank()) ingredients.add(meal.strMeasure16.plus(" ").plus(meal.strIngredient16))
            if (!meal.strIngredient17.isNullOrBlank()) ingredients.add(meal.strMeasure17.plus(" ").plus(meal.strIngredient17))
            if (!meal.strIngredient18.isNullOrBlank()) ingredients.add(meal.strMeasure18.plus(" ").plus(meal.strIngredient18))
            if (!meal.strIngredient19.isNullOrBlank()) ingredients.add(meal.strMeasure19.plus(" ").plus(meal.strIngredient19))
            if (!meal.strIngredient20.isNullOrBlank()) ingredients.add(meal.strMeasure20.plus(" ").plus(meal.strIngredient20))

            return ingredients.toList()
        }
    }
}