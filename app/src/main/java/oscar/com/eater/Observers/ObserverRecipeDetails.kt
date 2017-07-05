package oscar.com.eater.Observers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import oscar.com.eater.Activities.RecipeDetailsActivity
import oscar.com.eater.ApplicationContants
import oscar.com.eater.Holder.RecipeHolder
import oscar.com.eater.Interfaces.AbstractObserver
import oscar.com.eater.Pojo.Directions
import oscar.com.eater.Pojo.DirectionsWrapper
import oscar.com.eater.Pojo.IngredientWrapper
import oscar.com.eater.Pojo.InnerRecipeTypeWrapper
import oscar.com.eater.Response.RecipeDetailsResponse

/**
 * Created by omenji on 5/24/17.
 */
class ObserverRecipeDetails : AbstractObserver(), Observer<String> {

    private var mContext : Context? = null
    private var mRecipeHolder : RecipeHolder? = null
    private var mAcitivty : Context? = null
    private var mURL : String? = null

    override fun onSubscribe(d: Disposable?) {

    }

    fun forHolder(holder: RecipeHolder ) : ObserverRecipeDetails{
        mRecipeHolder = holder
        return this
    }

    override fun onNext(value: String?) {
        try {
            var gsonBuilder = GsonBuilder()
            gsonBuilder.registerTypeAdapter(InnerRecipeTypeWrapper::class.java, InnerRecipeTypeWrapper())
            gsonBuilder.registerTypeAdapter(Directions::class.java, Directions())
            var jsonResponse = gsonBuilder.create()
            jsonResponse
            var returnClassType = RecipeDetailsResponse().javaClass
            var recipeDetailsResponse = jsonResponse.fromJson(value, returnClassType)
            mRecipeHolder!!.setPrepTimeText(String.format("%s mins", recipeDetailsResponse.details!!.prepTime))
            mRecipeHolder!!.Description.text = recipeDetailsResponse.details!!.recipeDescription
            mRecipeHolder!!.RecipeTypes.text = recipeDetailsResponse.details!!.recipeTypesWrapper!!.toString()
            mRecipeHolder!!.recipeImageView.setOnClickListener({
                val intent = Intent(mContext, RecipeDetailsActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable(ApplicationContants.recipeDetails, recipeDetailsResponse)
                if(mURL != null){
                    bundle.putSerializable(ApplicationContants.recipeImageUrl,mURL)
                }
                intent.putExtras(bundle)
                mAcitivty?.startActivity(intent)
            })

        } catch (e : Exception) {
            e.printStackTrace()
            Log.e("ObserverRecipeDetails",String.format("Error decoding following json: %s",value))
        }
    }

    override fun onError(e: Throwable?) {

    }

    override fun onComplete() {

    }

    override fun withContext(context: Context?): ObserverRecipeDetails {
        mContext = context
        return this
    }

    fun withActivity(activity: Context?) : ObserverRecipeDetails {
        mAcitivty = activity
        return this
    }

    fun withImageUrl(url: String?) : ObserverRecipeDetails {
        mURL = url
        return this
    }


}