package oscar.com.eater.Observers

import android.content.Context
import android.widget.Adapter
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import oscar.com.eater.Holder.RecipeHolder
import oscar.com.eater.Interfaces.AbstractObserver
import oscar.com.eater.Response.RecipeDetailsResponse

/**
 * Created by omenji on 5/24/17.
 */
class ObserverRecipeDetails : AbstractObserver(), Observer<String> {

    private var mContext : Context? = null
    private var mRecipeHolder : RecipeHolder? = null;
    override fun onSubscribe(d: Disposable?) {

    }

    fun forHolder(holder: RecipeHolder ) : ObserverRecipeDetails{
        mRecipeHolder = holder
        return this
    }

    override fun onNext(value: String?) {
        var jsonResponse = Gson()
        var returnClassType = RecipeDetailsResponse().javaClass
        var recipeDetailsResponse = jsonResponse.fromJson(value,returnClassType)
        mRecipeHolder!!.setPrepTimeText(String.format("%s mins", recipeDetailsResponse.details!!.prepTime))
        mRecipeHolder!!.Description.setText(recipeDetailsResponse.details!!.recipeDescription)
        mRecipeHolder!!.RecipeTypes.setText(recipeDetailsResponse.details!!.recipeTypesWrapper!!.toString())
    }

    override fun onError(e: Throwable?) {

    }

    override fun onComplete() {

    }

    override fun withContext(context: Context?): ObserverRecipeDetails {
        mContext = context
        return this
    }
}