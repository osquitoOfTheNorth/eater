package oscar.com.eater.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.Toast;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import oscar.com.eater.adapter.RecipeIngredientsAdapter;
import oscar.com.eater.ApplicationContants;
import oscar.com.eater.pojo.RecipeWrapper;
import oscar.com.eater.R;
import oscar.com.eater.pojo.SavedRecipe;
import oscar.com.eater.pojo.User;
import oscar.com.eater.views.QueRicoTextView;
import oscar.com.eater.views.RatingView;
import oscar.com.eater.views.RecipeMetricView;

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
    private FloatingActionButton mSaveRecipeButton;
    private FloatingActionButton mScheduleRecipeButton;
    private List<SavedRecipe> mSavedRecipes = new ArrayList();
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
        mRecipeInstructions.setAdapter(adapter);
        mRecipeInstructions.setLayoutManager(new LinearLayoutManager(getContext()));
        loadImageIntoImageView(detailRecipe.getRecipe().getRecipeImage(),mImageViewBanner);
        mRecipeTypesTextbox =  v.findViewById(R.id.recipe_description);
        mRecipeTypesTextbox.setText(detailRecipe.getRecipe().getSource());
        final String recipeUrl = detailRecipe.getRecipe().getUri();
        final String recipeName = detailRecipe.getRecipe().getRecipeName();

        View.OnClickListener scheduleButtonClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().add(DateTimePickerDialogFragment.Companion.getInstance(recipeUrl),
                        DateTimePickerDialogFragment.Companion.getTag()).commit();
            }
        };

        final OnFailureListener failureListener = new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), R.string.unable_to_save_recipe, Toast.LENGTH_LONG).show();
            }
        };

        final OnSuccessListener onSuccessListener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(getContext(), R.string.recipe_saved, Toast.LENGTH_LONG).show();
            }
        };

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        View.OnClickListener saveRecipeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                mSavedRecipes.add(new SavedRecipe(recipeName, recipeUrl));
                FirebaseDatabase.getInstance("https://querico-53ad8.firebaseio.com")
                        .getReference("/users")
                        .child(uid)
                        .setValue(new User(email, userName, mSavedRecipes))
                        .addOnFailureListener(failureListener)
                        .addOnSuccessListener(onSuccessListener);
            }
        };
        mScheduleRecipeButton = v.findViewById(R.id.schedule_recipe_button);
        mScheduleRecipeButton.setOnClickListener(scheduleButtonClickListener);

        mSaveRecipeButton = v.findViewById(R.id.save_recipe_button);
        mSaveRecipeButton.setOnClickListener(saveRecipeClickListener);
        //Need users data before allowing to save. Op is asynchronus so disable for now and then enable once this info is
        //available.
        mSaveRecipeButton.setEnabled(false);


        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<HashMap<String,String>> savedRecipes = (List<HashMap<String,String>>)
                        dataSnapshot.child(uid).child("recipes").getValue();
                for(HashMap<String, String> savedRecipe: savedRecipes){
                    mSavedRecipes.add(new SavedRecipe(savedRecipe.get("name"),
                            savedRecipe.get("url")));
                }
                mSaveRecipeButton.setEnabled(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), R.string.unable_to_load_user_date, Toast.LENGTH_LONG).show();
            }
        };

        FirebaseDatabase.getInstance("https://querico-53ad8.firebaseio.com")
                .getReference("/users")
                .addListenerForSingleValueEvent(listener);
        return v;


    }


    private void loadImageIntoImageView(String srcUrl, ImageView view){
       Picasso.get().load(srcUrl).into(view);
    }
}
