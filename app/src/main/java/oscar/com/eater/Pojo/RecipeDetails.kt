package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.ResponseCache

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
    var nutritionalInformation : NutritionalInfoWrapper = NutritionalInfoWrapper()

    fun getMetrics() : ArrayList<RecipeMetric> {
        var returnVal : ArrayList<RecipeMetric> = ArrayList()
        var metric1 : RecipeMetric = RecipeMetric()
        var metric2 : RecipeMetric = RecipeMetric()
        var metric3 : RecipeMetric = RecipeMetric()
        metric1.UnitNumber = prepTime!!.toString()
        metric1.UnitOfMeasurement = "Minutes"
        metric2.UnitNumber = nutritionalInformation.nutritionalInfoInner.caloriesPerServing.toString()
        metric2.UnitOfMeasurement = "Calories"
        returnVal.add(metric1)
        returnVal.add(metric2)
        return returnVal
    }

}