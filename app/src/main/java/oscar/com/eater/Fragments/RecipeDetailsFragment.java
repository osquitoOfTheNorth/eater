package oscar.com.eater.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import oscar.com.eater.ApplicationContants;
import oscar.com.eater.Observables.ObservableAnalyzedInstructionsRequest;
import oscar.com.eater.Observables.ObservableImageRequest;
import oscar.com.eater.Observers.ObserverAnalyzedInstructions;
import oscar.com.eater.Observers.ObserverImageDownloader;
import oscar.com.eater.Pojo.Recipe;
import oscar.com.eater.R;

/**
 * Created by omenji on 3/29/17.
 */

public class RecipeDetailsFragment extends Fragment {

    private ImageView mImageViewBanner;
    private ListView mRecipeInstructions;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.recipe_detail_fragment,container,false);
        Bundle bundle = getArguments();
        Recipe detailRecipe = (Recipe) bundle.getSerializable(ApplicationContants.getRecipeSerializableKey());
        mImageViewBanner = (ImageView) v.findViewById(R.id.recipe_image_view_holder_detail_page);
        mRecipeInstructions = (ListView) v.findViewById(R.id.recipe_steps);
        loadImageIntoImageView(detailRecipe.getImageSrcUrl(),mImageViewBanner);
        Observable.create(new ObservableAnalyzedInstructionsRequest(detailRecipe.getId()))
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(getParameterizedObserver());
        return v;


    }
    private ObserverAnalyzedInstructions getParameterizedObserver(){
        return ObserverAnalyzedInstructions.Build().withListView(mRecipeInstructions).withContext(getActivity());
    }
    private void loadImageIntoImageView(String srcUrl, ImageView view){
        Observable.create(new ObservableImageRequest(srcUrl))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImageDownloader(view, AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in_animation)));

    }
}
