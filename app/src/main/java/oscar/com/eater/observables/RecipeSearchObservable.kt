package oscar.com.eater.observables
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import oscar.com.eater.pojo.RecipeQuery
import oscar.com.eater.pojo.RecipeResponse
import oscar.com.eater.request.CustomCallback
import oscar.com.eater.request.ServiceAccessor


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
