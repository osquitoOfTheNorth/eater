package oscar.com.eater.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import oscar.com.eater.R
import oscar.com.eater.pojo.SavedRecipe

class SavedRecipeViewHolder(private val v: View): RecyclerView.ViewHolder(v) {

    fun bindSavedRecipe(savedRecipe: SavedRecipe){
        v.findViewById<TextView>(R.id.saved_recipe_name).text = savedRecipe.name
    }
}