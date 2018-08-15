package oscar.com.eater.ViewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import oscar.com.eater.Observables.RecipeSearchObservable
import oscar.com.eater.Pojo.RecipeQuery
import oscar.com.eater.Pojo.RecipeResponse
import oscar.com.eater.Pojo.RecipeWrapper

/**
 * Created by oscmenji on 2018-04-07.
 */
class RecipeWallViewModel : ViewModel(), Observer<RecipeResponse> {

    private var recipes : MutableLiveData<List<RecipeWrapper>> = MutableLiveData()
    private var from : Int = 0
    private var to : Int = 20
    private var errorData: MutableLiveData<String> = MutableLiveData()
    private var noResultsReturned: MutableLiveData<String> = MutableLiveData()
    private var query = ""
    init {
        recipes.value = ArrayList()
    }
    fun observeRecipesForQueryString() : LiveData<List<RecipeWrapper>>{
        return recipes
    }

    fun getRecipesForQueryString(string : String, getMore: Boolean) : List<RecipeWrapper> {
        query = string
        if(recipes.value?.isEmpty()!! || getMore) {
            Observable.create(RecipeSearchObservable(RecipeQuery(from,to,string)))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this)
        }
        return recipes.value ?: ArrayList()
    }

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable?) {

    }

    override fun onNext(value: RecipeResponse?) {
        value?.let{
            if(it.totalResults <= 0){
                noResultsReturned.postValue(String.format("Sorry, no results returned for the query: %s", query))
            } else {
                from = it.to
                to = it.to + 20
                recipes.value = recipes.value?.plus(it.recipes)
            }
        }

    }

    override fun onError(e: Throwable?) {
        e?.printStackTrace()
        errorData.postValue("Sorry, something went wrong trying to retrieve your recipes")
    }

    fun observeErrors() : MutableLiveData<String> {
        return errorData
    }

    fun observeNoResultsReturned() :MutableLiveData<String> {
        return noResultsReturned
    }
}
