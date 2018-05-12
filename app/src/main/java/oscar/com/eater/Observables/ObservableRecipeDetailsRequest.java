package oscar.com.eater.Observables;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
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

    }
}
