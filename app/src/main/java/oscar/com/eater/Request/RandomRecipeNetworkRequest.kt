package oscar.com.eater.Request
import okhttp3.Request

/**
 * Created by omenji on 3/16/17.
 */

class RandomRecipeNetworkRequest : NetworkRequest("GET", hashMapOf("method" to "recipes.search") ) {

    fun getRandomRecipes(numberOfResults: Int): Request {
        mHttpBuilder.addQueryParameter("method", "recipes.search")
        return Request.Builder().url(mHttpBuilder.build()).build()
    }
}
