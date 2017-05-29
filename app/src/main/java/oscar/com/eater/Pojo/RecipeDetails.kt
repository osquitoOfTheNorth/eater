package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by omenji on 5/24/17.
 */
class RecipeDetails :Serializable {

    @SerializedName("recipe_name")
    var foodName : String? = null

    @SerializedName("preparation_time_min")
    var prepTime : Int? = null

    @SerializedName("recipe_types")
    var recipeTypesWrapper : RecipeTypeWrapper? = null

    @SerializedName("recipe_description")
    var recipeDescription: String? = null

    var recipeImage: String? = null

    @SerializedName("rating")
    var rating : Int? = null

    @SerializedName("serving_sizes")
    var nutritionalInformation : NutritionalInfo? = null



}