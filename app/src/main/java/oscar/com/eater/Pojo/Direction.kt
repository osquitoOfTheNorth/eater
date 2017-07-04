package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by omenji on 7/4/17.
 */
class Direction : Serializable {

    @SerializedName("direction_description")
    var instruction = ""
    @SerializedName("direction_number")
    var instructionStepNumber = 0
}