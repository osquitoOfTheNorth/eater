package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * Created by omenji on 3/16/17.
 */

class Recipe : Serializable {

    @SerializedName("recipe_id")
    var id: Int = 0
    @SerializedName("recipe_image")
    var recipeImage: String? = null
    @SerializedName("recipe_name")
    var recipeName: String? = null


}
