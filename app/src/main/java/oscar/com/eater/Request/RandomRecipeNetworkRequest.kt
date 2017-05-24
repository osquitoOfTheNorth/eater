package oscar.com.eater.Request
import okhttp3.Request

/**
 * Created by omenji on 3/16/17.
 */

class RandomRecipeNetworkRequest(numberOfResults: Int) : NetworkRequest("GET", hashMapOf("method" to "recipes.search", "max_results" to numberOfResults.toString()) ) {

    private var mNumberResults = numberOfResults
    fun getRandomRecipes(): Request {
        mHttpBuilder.addQueryParameter("method", "recipes.search")
        mHttpBuilder.addQueryParameter("max_results",mNumberResults.toString())
        return Request.Builder().url(mHttpBuilder.build()).build()
    }
}
