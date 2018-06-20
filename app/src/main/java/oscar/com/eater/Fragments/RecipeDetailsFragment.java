package oscar.com.eater.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Random;

import oscar.com.eater.Adapter.RecipeIngredientsAdapter;
import oscar.com.eater.ApplicationContants;
import oscar.com.eater.Pojo.RecipeWrapper;
import oscar.com.eater.R;
import oscar.com.eater.Views.QueRicoTextView;
import oscar.com.eater.Views.RatingView;
import oscar.com.eater.Views.RecipeMetricView;

/**
 * Created by omenji on 3/29/17.
 */

public class RecipeDetailsFragment extends Fragment {

    private ImageView mImageViewBanner;
    private RecyclerView mRecipeInstructions;
    private RatingView mRatingView;
    private RecipeMetricView metricView;
    private QueRicoTextView mRecipeTitle;
    private QueRicoTextView mRecipeTypesTextbox;

    public static final String TAG = "RecipeDetailsFragment";

    public static RecipeDetailsFragment getInstance(RecipeWrapper wrapper){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ApplicationContants.INSTANCE.getRecipeDetails(), wrapper);
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.recipe_detail_fragment,container,false);
        Bundle bundle = getArguments();
        RecipeWrapper detailRecipe = (RecipeWrapper) bundle.getSerializable(ApplicationContants.INSTANCE.getRecipeDetails());
        mImageViewBanner = v.findViewById(R.id.recipe_image_view_holder_detail_page);
        mRecipeInstructions =  v.findViewById(R.id.recipe_steps);
        metricView = v.findViewById(R.id.recipe_metrics);
        metricView.setMetrics(detailRecipe.getRecipe().getRecipeMetrics());
        mRatingView =  v.findViewById(R.id.rating_view);
        mRatingView.setMaxRating(5);
        //This api doesnt support the notion of a rating so I have to randomly make this shit up
        //So pointless.
        mRatingView.setTotalRating(new Random().nextInt(6));
        metricView = v.findViewById(R.id.recipe_metrics);
        mRecipeTitle =  v.findViewById(R.id.recipe_title);
        mRecipeTitle.setText(detailRecipe.getRecipe().getRecipeName());
        RecipeIngredientsAdapter adapter = new RecipeIngredientsAdapter(detailRecipe.getRecipe().getIngredients());
        adapter.notifyDataSetChanged();
        mRecipeInstructions.setAdapter(adapter);

        loadImageIntoImageView(detailRecipe.getRecipe().getRecipeImage(),mImageViewBanner);
        mRecipeTypesTextbox =  v.findViewById(R.id.recipe_description);
        mRecipeTypesTextbox.setText(detailRecipe.getRecipe().getSource());
        return v;


    }


    private void loadImageIntoImageView(String srcUrl, ImageView view){
       Picasso.get().load(srcUrl).into(view);
    }
}
