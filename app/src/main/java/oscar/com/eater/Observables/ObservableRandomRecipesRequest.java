package oscar.com.eater.Observables;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import oscar.com.eater.Request.RandomRecipeNetworkRequest;

/**
 * Created by omenji on 3/21/17.
 */

public class ObservableRandomRecipesRequest implements ObservableOnSubscribe<String>{

    @Override
    public void subscribe(ObservableEmitter<String> e) throws Exception {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(new RandomRecipeNetworkRequest(10).getRandomRecipes()).execute();
            e.onNext(response.body().string());
            e.onComplete();
    }

}
