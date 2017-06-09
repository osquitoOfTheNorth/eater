package oscar.com.eater.Pojo

import java.io.Serializable

/**
 * Created by Oscar on 6/9/2017.
 */
class Ingredient :Serializable {
    var food_id : Int = 0
    var food_name : String = ""
    var ingredient_description : String = ""
    var ingredient_url : String = ""
    var measurement_description : String = ""
    var number_of_units : Float = 0.0f
    var serving_id : Int = 0
}