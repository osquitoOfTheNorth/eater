package oscar.com.eater.pojo

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


    @SerializedName("recipe_description")
    var recipeDescription: String? = null

    var recipeImage: String? = null

    @SerializedName("rating")
    var rating : Int? = null

    @SerializedName("serving_sizes")
    var nutritionalInformation : NutritionalInfoWrapper = NutritionalInfoWrapper()

    @SerializedName("ingredients")
    var ingredientsWrapper : IngredientWrapper = IngredientWrapper()


    @SerializedName("directions")
    var recipeDirections : DirectionsWrapper = DirectionsWrapper()

    fun getMetrics() : ArrayList<RecipeMetric> {
        var returnVal : ArrayList<RecipeMetric> = ArrayList()
        var metric1 : RecipeMetric = RecipeMetric()
        var metric2 : RecipeMetric = RecipeMetric()
        var metric3 : RecipeMetric = RecipeMetric()
        metric1.UnitNumber = prepTime!!.toString()
        metric1.UnitOfMeasurement = "Minutes"
        metric2.UnitNumber = nutritionalInformation.nutritionalInfoInner.caloriesPerServing.toString()
        metric2.UnitOfMeasurement = "Calories"
        metric3.UnitNumber = ingredientsWrapper.ingredients.size.toString()
        metric3.UnitOfMeasurement = "Ingredients"
        returnVal.add(metric1)
        returnVal.add(metric2)
        returnVal.add(metric3)
        return returnVal
    }

}