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
import java.util.ArrayList;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import oscar.com.eater.Adapter.RecipeInstructionsAdapter;
import oscar.com.eater.ApplicationContants;
import oscar.com.eater.Observables.ObservableImageRequest;
import oscar.com.eater.Observers.ObserverImageDownloader;
import oscar.com.eater.Pojo.Instruction;
import oscar.com.eater.R;
import oscar.com.eater.Response.RecipeDetailsResponse;
import oscar.com.eater.Views.QueRicoTextView;
import oscar.com.eater.Views.RatingView;
import oscar.com.eater.Views.RecipeMetricView;

/**
 * Created by omenji on 3/29/17.
 */

public class RecipeDetailsFragment extends Fragment {

    private ImageView mImageViewBanner;
    private ListView mRecipeInstructions;
    private RatingView mRatingView;
    private RecipeMetricView metricView;
    private QueRicoTextView mRecipeTitle;
    private QueRicoTextView mRecipeTypesTextbox;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.recipe_detail_fragment,container,false);
        Bundle bundle = getArguments();
        RecipeDetailsResponse detailRecipe = (RecipeDetailsResponse) bundle.getSerializable(ApplicationContants.INSTANCE.getRecipeDetails());
        String mUrl = (String) bundle.getSerializable(ApplicationContants.INSTANCE.getRecipeImageUrl());
        mImageViewBanner = (ImageView) v.findViewById(R.id.recipe_image_view_holder_detail_page);
        mRecipeInstructions = (ListView) v.findViewById(R.id.recipe_steps);

        mRatingView = (RatingView) v.findViewById(R.id.rating_view);
        mRatingView.setMaxRating(5);
        mRatingView.setTotalRating(detailRecipe.getDetails().getRating());
        metricView = (RecipeMetricView) v.findViewById(R.id.recipe_metrics);
        metricView.setMetrics(detailRecipe.getDetails().getMetrics());
        mRecipeTitle = (QueRicoTextView) v.findViewById(R.id.recipe_title);
        mRecipeTitle.setText(detailRecipe.getDetails().getFoodName());
        mRecipeInstructions.setAdapter(new RecipeInstructionsAdapter(getContext(),detailRecipe.getDetails().getRecipeDirections().getStepByStepDirections()));
        loadImageIntoImageView(mUrl,mImageViewBanner);
        mRecipeTypesTextbox = (QueRicoTextView) v.findViewById(R.id.recipe_description);
        mRecipeTypesTextbox.setText(detailRecipe.getDetails().getRecipeDescription());
        return v;


    }


    private void loadImageIntoImageView(String srcUrl, ImageView view){
        Observable.create(new ObservableImageRequest(srcUrl))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImageDownloader(view, AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.fade_in_animation)));

    }
}
