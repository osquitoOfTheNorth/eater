package oscar.com.eater.holder

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import oscar.com.eater.R
import oscar.com.eater.activities.ScheduledRecipeActivity
import oscar.com.eater.activities.ScheduledRecipeActivity.Companion.recipeKey
import oscar.com.eater.pojo.SavedRecipe
class SavedRecipeViewHolder(private val v: View, private val context: Context): RecyclerView.ViewHolder(v) {

    fun bindSavedRecipe(savedRecipe: SavedRecipe){
        v.findViewById<TextView>(R.id.saved_recipe_name).text = savedRecipe.name
        val intent = Intent()
        intent.setClass(context, ScheduledRecipeActivity::class.java)
        intent.putExtra(recipeKey, savedRecipe.url)
        v.setOnClickListener { context.startActivity(intent) }
    }
}