package oscar.com.eater.Observables;
import com.google.gson.GsonBuilder;

import java.util.List;


import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import oscar.com.eater.Pojo.Instruction;
import oscar.com.eater.Request.AnalyzedIntructionsRequest;

/**
 * Created by omenji on 3/29/17.
 */

public class ObservableAnalyzedInstructionsRequest implements ObservableOnSubscribe<String> {
    private int mRecipeId;

    public ObservableAnalyzedInstructionsRequest(int recipeId){
        mRecipeId = recipeId;
    }

    @Override
    public void subscribe(ObservableEmitter<String> e) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request httpRequest = new AnalyzedIntructionsRequest(mRecipeId).getAnalyzedInstructions();
        Response response = client.newCall(httpRequest).execute();
        e.onNext(response.body().string());
        e.onComplete();
    }
}


