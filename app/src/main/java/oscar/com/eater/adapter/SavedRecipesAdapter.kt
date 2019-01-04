package oscar.com.eater.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import oscar.com.eater.R
import oscar.com.eater.holder.SavedRecipeViewHolder
import oscar.com.eater.pojo.SavedRecipe

class SavedRecipesAdapter(private val context: Context,
                          private var savedRecipes: List<SavedRecipe>) : RecyclerView.Adapter<SavedRecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedRecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.saved_recipe_layout_vh, parent, false)
        return SavedRecipeViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return savedRecipes.size
    }

    override fun onBindViewHolder(holder: SavedRecipeViewHolder, position: Int) {
        holder.bindSavedRecipe(savedRecipes[position])
    }

    fun update(mySavedRecipes:List<SavedRecipe>){
        savedRecipes = mySavedRecipes
        notifyDataSetChanged()
    }


}