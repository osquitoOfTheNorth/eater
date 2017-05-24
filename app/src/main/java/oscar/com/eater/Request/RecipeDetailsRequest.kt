package oscar.com.eater.Request

import okhttp3.Request

/**
 * Created by omenji on 5/24/17.
 */
class RecipeDetailsRequest(val recipeId: Int) : NetworkRequest("GET", hashMapOf("method" to "recipe.get", "recipe_id" to recipeId.toString())){

    fun getRecipeDetails() : Request{
        mHttpBuilder.addQueryParameter("method","recipe.get")
        mHttpBuilder.addQueryParameter("recipe_id", recipeId.toString())
        return Request.Builder().url(mHttpBuilder.build()).build()
    }
}