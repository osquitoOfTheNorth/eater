package oscar.com.eater.pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Oscar on 6/9/2017.
 */
class NutritionalInfoWrapper : Serializable{

    @SerializedName("serving")
    var nutritionalInfoInner : NutritionalInfo = NutritionalInfo()

}