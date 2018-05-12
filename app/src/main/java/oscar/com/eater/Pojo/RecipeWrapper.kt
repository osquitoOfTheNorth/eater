package oscar.com.eater.Pojo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by oscmenji on 2018-04-22.
 */
class RecipeWrapper(
        @SerializedName("recipe")
        val recipe : Recipe,
        @SerializedName("bookmarked")
        val isBookmarked : Boolean,
        @SerializedName("bought")
        val isBought : Boolean
) : Serializable