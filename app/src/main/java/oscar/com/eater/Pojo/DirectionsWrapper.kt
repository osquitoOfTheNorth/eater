package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName
import oscar.com.eater.Utils.DeserializationUtil
import oscar.com.eater.Utils.Wrapper
import java.io.Serializable

/**
 * Created by omenji on 7/4/17.
 */
class DirectionsWrapper : Serializable, Wrapper<Direction>, DeserializationUtil<Direction>(){
    override fun getWrapper(): Wrapper<Direction> {
        return this
    }

    override fun setWrapper(collection: ArrayList<Direction>) {
        stepByStepDirections = collection
    }

    @SerializedName("direction")
    var stepByStepDirections = ArrayList<Direction>()
}