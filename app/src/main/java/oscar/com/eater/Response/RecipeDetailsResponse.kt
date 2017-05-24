package oscar.com.eater.Response

import com.google.gson.annotations.SerializedName
import oscar.com.eater.Pojo.RecipeDetails

/**
 * Created by omenji on 5/24/17.
 */
class RecipeDetailsResponse {
    @SerializedName("recipe")
    var details : RecipeDetails? = null

}