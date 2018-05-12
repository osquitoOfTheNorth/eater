package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName

import java.io.Serializable

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

        fun getHealthLabelString() : String{
            return if(healthLabels.isEmpty()) ""
                   else healthLabels.reduce({
                    accumulation, element  -> accumulation.plus(" | ".plus(element))
            })
        }
}

