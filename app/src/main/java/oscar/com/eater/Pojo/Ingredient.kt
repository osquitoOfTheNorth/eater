package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Oscar on 6/9/2017.
 */
class Ingredient :Serializable {
    @SerializedName("quantity")
    var quantity : Float = 0.0f
    @SerializedName("measure")
    var measure : LabelHolder = LabelHolder()
    @SerializedName("food")
    var food : LabelHolder = LabelHolder()
}