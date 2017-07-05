package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by omenji on 7/4/17.
 */
class DirectionsWrapper : Serializable{
    @SerializedName("direction")
    var stepByStepDirections = Directions()




}