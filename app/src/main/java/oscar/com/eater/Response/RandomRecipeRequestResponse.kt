package oscar.com.eater.Response

import com.google.gson.annotations.SerializedName

import oscar.com.eater.Pojo.Recipe
import oscar.com.eater.Pojo.RecipesWrapper

/**
 * Created by omenji on 3/17/17.
 */

class RandomRecipeRequestResponse {

    @SerializedName("recipes")
    var products: RecipesWrapper? = null

}
