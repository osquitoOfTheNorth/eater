package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName

/**
 * Created by omenji on 5/24/17.
 */
class RecipeTypeWrapper {

    @SerializedName("recipe_type")
    var recipeTypes: Array<String>? = null


    override fun toString(): String {
        var sb =  StringBuilder()
        for(str in recipeTypes!!){
            sb.append(str)
        }
        return sb.toString()
    }
}