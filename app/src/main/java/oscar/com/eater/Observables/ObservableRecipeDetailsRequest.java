package oscar.com.eater.Observables;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import oscar.com.eater.Request.RecipeDetailsRequest;

/**
 * Created by omenji on 5/24/17.
 */

public class ObservableRecipeDetailsRequest implements ObservableOnSubscribe<String>{
    private int mRecipeId;

    public ObservableRecipeDetailsRequest(int recipeId){
        mRecipeId = recipeId;
    }
    @Override
    public void subscribe(ObservableEmitter<String> e) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(new RecipeDetailsRequest(mRecipeId).getRecipeDetails()).execute();
        e.onNext(response.body().string());
        e.onComplete();
    }
}
