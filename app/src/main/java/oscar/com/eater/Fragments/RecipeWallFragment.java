package oscar.com.eater.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import oscar.com.eater.Observables.ObservableRandomRecipesRequest;
import oscar.com.eater.Observers.ObserverRecipeWall;
import oscar.com.eater.R;

/**
 * Created by omenji on 3/16/17.
 */

public class RecipeWallFragment extends Fragment {
    private RecyclerView mRecyclerRecipeView;
    private ImageView mLoadingIcon;
    private int loadingiconID;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingiconID = 0;
        View v = inflater.inflate(R.layout.recipe_wall_fragment,container,false);
        mRecyclerRecipeView = (RecyclerView) v.findViewById(R.id.fragment_recipe_recycler_view_holder);
        mLoadingIcon = (ImageView) v.findViewById(R.id.loading_icon);
        getRecipesRequest(false);
        mRecyclerRecipeView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerRecipeView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!mRecyclerRecipeView.canScrollHorizontally(1)){
                    getRecipesRequest(true);
                }
            }
        });

        return v;
    }


    private void getRecipesRequest(boolean addToExistingRecipes){
        mLoadingIcon.setVisibility(View.VISIBLE);
        mLoadingIcon.setImageDrawable(getLoadingIconDrawable());
        mLoadingIcon.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_animation));
        mRecyclerRecipeView.startAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.fade_out_animation));

        ObserverRecipeWall observer = ObserverRecipeWall.Build().SpecifyRecyclerView(mRecyclerRecipeView)
                .withContext(getActivity())
                .withAppendingResults(addToExistingRecipes)
                .withLoader(mLoadingIcon);


        Observable.create(new ObservableRandomRecipesRequest())
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(observer);

    }

    private Drawable getLoadingIconDrawable(){
        loadingiconID++;
        return loadingiconID % 2 == 0 ? getActivity().getDrawable(R.drawable.loading_image1) : getActivity().getDrawable(R.drawable.loading_image2);
    }

}
