package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName

import java.util.ArrayList

/**
 * Created by omenji on 5/18/17.
 */

public class RecipesWrapper{
    @SerializedName("recipe")
    var recipes:  ArrayList<Recipe>? = null
    @SerializedName("page_number")
    var pageNumber: Int = 0
}
