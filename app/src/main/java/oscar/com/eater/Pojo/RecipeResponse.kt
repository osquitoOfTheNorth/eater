package oscar.com.eater.Pojo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by omenji on 5/24/17.
 */
class RecipeResponse(

    @SerializedName("hits")
    var recipes : List<RecipeWrapper>,

    @SerializedName("from")
    var from : Int,

    @SerializedName("to")
    var to: Int,

    @SerializedName("count")
    var totalResults : Int
) :Serializable