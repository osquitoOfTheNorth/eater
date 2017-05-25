package oscar.com.eater.Pojo
import com.google.gson.annotations.SerializedName

/**
 * Created by omenji on 5/24/17.
 */
class RecipeTypeWrapper() {

    @SerializedName("recipe_type")
    var recipeTypesInner : InnerRecipeTypeWrapper? = null
    override fun toString(): String {
        var sb =  StringBuilder()
        var itemCount = recipeTypesInner!!.recipeTypesInnerList.count()
        for(str in recipeTypesInner!!.recipeTypesInnerList){
            sb.append(str)
            if(itemCount != 1) {
                sb.append(" | ")
            }
        }
        return sb.toString()
    }

}