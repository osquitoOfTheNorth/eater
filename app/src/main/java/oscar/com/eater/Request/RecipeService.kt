package oscar.com.eater.Request

import oscar.com.eater.Pojo.RecipeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by oscmenji on 2018-04-01.
 */
interface RecipeService {

    @GET("/search")
    fun getRecipesForString(@Query("q") searchString : String,
                            @Query("from") from :Int,
                            @Query("to") to : Int) : Call<RecipeResponse>
}