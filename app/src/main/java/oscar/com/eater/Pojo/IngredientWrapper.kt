package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Oscar on 6/9/2017.
 */
class IngredientWrapper : Serializable {
    @SerializedName("ingredient")
    var ingredients : List<Ingredient> = ArrayList()
}