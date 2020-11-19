package ar.com.ualaentrevista.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.ualaentrevista.R
import ar.com.ualaentrevista.model.Meal
import com.squareup.picasso.Picasso

class RecipeItemAdapter constructor(
    private val recipes: List<Meal>,
    private val context: Context,
    private val listner: MealClickedListener
)
    : RecyclerView.Adapter<RecipeItemAdapter.MealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealItemViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_home_recipes, parent, false)
        return MealItemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: MealItemViewHolder, position: Int) {
        with(holder) {
            with(recipes[position]) {
                recipeName.text = strMeal
                recipeCategory.text = strCategory
                Picasso.with(context)
                    .load(strMealThumb)
                    .placeholder(R.color.transparent)
                    .error(R.color.transparent)
                    .into(recipeImage)
                itemView.setOnClickListener { listner.mealClicked(this) }
            }
        }
    }

    class MealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById<View>(R.id.recipeImage) as ImageView
        val recipeName: TextView = itemView.findViewById<View>(R.id.recipeName) as TextView
        val recipeCategory: TextView = itemView.findViewById<View>(R.id.recipeCategory) as TextView
    }
}