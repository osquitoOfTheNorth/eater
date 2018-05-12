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
    private var to : Int = 10
    fun getRecipesForQueryString(string : String, getMore : Boolean) : LiveData<List<RecipeWrapper>>{
        if(getMore){
            recipes = MutableLiveData()
        }
        if(recipes.value == null || recipes.value?.isEmpty()!! || getMore) {
                Observable.create(RecipeSearchObservable(RecipeQuery(from,to,string)))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this)
        }
        return recipes
    }

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable?) {

    }

    override fun onNext(value: RecipeResponse?) {
        value?.let{
            from = value.to
            to = value.to + 10
            recipes.postValue(value.recipes)
        }

    }

    override fun onError(e: Throwable?) {
        e?.printStackTrace()
        recipes.postValue(ArrayList())
    }
}