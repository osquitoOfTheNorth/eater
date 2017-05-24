package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName

/**
 * Created by omenji on 5/24/17.
 */
class RecipeDetails {

    @SerializedName("recipe_name")
    var foodName : String? = null

    @SerializedName("preparation_time_min")
    var prepTime : Int? = null

    @SerializedName("recipe_types")
    var recipeTypesWrapper : RecipeTypeWrapper? = null

    @SerializedName("recipe_description")
    var recipeDescription: String? = null

}