package oscar.com.eater.Observables
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import oscar.com.eater.Pojo.RecipeQuery
import oscar.com.eater.Pojo.RecipeResponse
import oscar.com.eater.Request.CustomCallback
import oscar.com.eater.Request.ServiceAccessor


/**
 * Created by omenji on 3/21/17.
 */

class RecipeSearchObservable(query : RecipeQuery) : ObservableOnSubscribe<RecipeResponse> {
    private var query : RecipeQuery = query
    @Throws(Exception::class)
    override fun subscribe(e: ObservableEmitter<RecipeResponse>) {
        ServiceAccessor.recipeService.getRecipesForString(query.query, query.from, query.to).enqueue(CustomCallback(e))
    }


}
