package oscar.com.eater.request

import io.reactivex.ObservableEmitter
import oscar.com.eater.pojo.QueRicoError
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by oscmenji on 2018-04-08.
 */
class CustomCallback<T>(e : ObservableEmitter<T>) : Callback<T> {
    private val observableEmitter = e


    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        response?.let{
            if(it.isSuccessful) {
                observableEmitter.onNext(response.body())
            } else {
                observableEmitter.onError(QueRicoError(response.code(),response.message()))
            }
        }

    }

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        observableEmitter.onError(t)
    }
}