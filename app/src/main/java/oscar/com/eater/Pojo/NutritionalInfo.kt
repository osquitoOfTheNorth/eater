package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by omenji on 5/26/17.
 */
class NutritionalInfo : Serializable{
    @SerializedName("calories")
    var caloriesPerServing : Int? = null
    var calcium : Float? = null
    var carbohydrates : Float? = null
    var cholesterol : Float? = null
    var fat : Float? = null
    var fibre : Float? = null
    var iron : Float? = null
    var monoFats : Float? = null
    var polyFats : Float? = null
    var potassium : Float? = null
    var protein : Float? = null
    var satFat : Float? = null
    var servingSize : String? = null
    var sodium : Float? = null
    var sugar : Float? = null
    var transFat : Float? = null
    var vitaminC : Float? = null
}