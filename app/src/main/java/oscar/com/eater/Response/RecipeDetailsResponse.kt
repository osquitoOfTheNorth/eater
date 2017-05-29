package oscar.com.eater.Response

import com.google.gson.annotations.SerializedName
import oscar.com.eater.Pojo.RecipeDetails
import java.io.Serializable

/**
 * Created by omenji on 5/24/17.
 */
class RecipeDetailsResponse : Serializable{
    @SerializedName("recipe")
    var details : RecipeDetails? = null

}