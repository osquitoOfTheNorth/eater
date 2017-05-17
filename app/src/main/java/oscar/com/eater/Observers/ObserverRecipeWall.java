package oscar.com.eater.Observers;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import oscar.com.eater.Interfaces.AbstractObserver;
import oscar.com.eater.Pojo.Recipe;
import oscar.com.eater.R;
import oscar.com.eater.Response.RandomRecipeRequestResponse;
import oscar.com.eater.Adapter.RecipeViewAdapter;

/**
 * Created by omenji on 3/21/17.
 */

public class ObserverRecipeWall extends AbstractObserver implements  Observer<String>{

    private RecyclerView mRecyclerView;
    private Context mContext;
    private boolean mAddToView;
    private ImageView mImageView;

    private ObserverRecipeWall(){

    }

    public static ObserverRecipeWall Build(){
        return new ObserverRecipeWall();
    }

    public ObserverRecipeWall SpecifyRecyclerView(RecyclerView view){
        mRecyclerView = view;
        return this;
    }

    public ObserverRecipeWall withContext(Context context){
        mContext = context;
        return this;
    }

    public ObserverRecipeWall withAppendingResults(boolean addToView){
        mAddToView = addToView;
        return this;
    }

    public ObserverRecipeWall withLoader(ImageView imageView){
        mImageView = imageView;
        return this;
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(String value) {
        Gson gson = new Gson();
        RandomRecipeRequestResponse responseDeserialized = gson.fromJson(value, RandomRecipeRequestResponse.class);
        List<Recipe> randomRecipes = responseDeserialized.getProducts();
        if(mAddToView){
            RecipeViewAdapter adapter = (RecipeViewAdapter) mRecyclerView.getAdapter();
            adapter.addToScrollable(randomRecipes);
        } else {
            RecipeViewAdapter adapter = new RecipeViewAdapter(randomRecipes,mContext);
            mRecyclerView.setAdapter(adapter);
        }
        if(mImageView != null) {
            mImageView.clearAnimation();
            mImageView.setVisibility(View.GONE);
        }
        mRecyclerView.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in_animation));
    }
    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
}
