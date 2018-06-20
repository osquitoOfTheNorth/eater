package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName

import java.io.Serializable
import java.text.NumberFormat

/**
 * Created by omenji on 3/16/17.
 */

class Recipe(
        @SerializedName("url")
        var uri: String = "",
        @SerializedName("image")
        var recipeImage: String = "",
        @SerializedName("label")
        var recipeName: String = "",
        @SerializedName("healthLabels")
        var healthLabels:  List<String> = ArrayList(),
        @SerializedName("yield")
        var servings : Float = 0F,
        @SerializedName("calories")
        var totalCal : Float = 0F,
        @SerializedName("totalTime")
        var totalTime : Float = 0F,
        @SerializedName("source")
        var source : String = "") :Serializable{


        @SerializedName("ingredients")
        var ingredients : Array<Ingredient> = arrayOf()

        private val numFormatter = NumberFormat.getInstance()

        fun getHealthLabelString() : String{
            return if(healthLabels.isEmpty()) ""
                   else healthLabels.reduce({
                    accumulation, element  -> accumulation.plus(" | ".plus(element))
            })
        }

        fun getRecipeMetrics() : ArrayList<RecipeMetric>{
            numFormatter.maximumFractionDigits = 0
            val metrics = ArrayList<RecipeMetric>()
            val numIngredients = RecipeMetric()
            numIngredients.UnitNumber = ingredients.size.toString()
            numIngredients.UnitOfMeasurement = "Ingredients"
            numIngredients.UnitSeperator = "|"
            val totalCal = RecipeMetric()
            totalCal.UnitOfMeasurement = "Calories"
            totalCal.UnitNumber = numFormatter.format((this.totalCal/servings))
            totalCal.UnitSeperator = "|"
            val totalMins = RecipeMetric()
            totalMins.UnitOfMeasurement = " Minutes"
            totalMins.UnitSeperator = "|"
            totalMins.UnitNumber = this.totalTime.toString()
            metrics.add(numIngredients)
            metrics.add(totalCal)
            metrics.add(totalMins)
            return metrics
        }
}

