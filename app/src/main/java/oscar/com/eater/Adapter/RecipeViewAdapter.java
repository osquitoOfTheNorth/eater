package oscar.com.eater.Adapter;


import android.arch.persistence.room.util.StringUtil;
import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.squareup.picasso.Picasso;

import java.util.List;
import oscar.com.eater.Pojo.Recipe;
import oscar.com.eater.Pojo.RecipeWrapper;
import oscar.com.eater.R;
import oscar.com.eater.Holder.RecipeHolder;

/**
 * Created by omenji on 3/16/17.
 */

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeHolder> {
    private List<RecipeWrapper> mRecipes;
    private Context mActivity;


    public RecipeViewAdapter(List<RecipeWrapper> recipes, Context activity){
        mRecipes = recipes;
        mActivity = activity;
    }


    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewForViewHolder = inflater.inflate(R.layout.recipe_view_holder, parent, false);
        return new RecipeHolder(viewForViewHolder);
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {
        final RecipeWrapper recipe = mRecipes.get(position);
        holder.setRecipeTitle(recipe.getRecipe().getRecipeName());
        holder.setRecipeImageViewPlaceHolder(mActivity.getDrawable(R.drawable.recipe_image_placeholder));
        if(recipe.getRecipe().getRecipeImage().length() > 0 ) {
            holder.setRecipeImageView(recipe);
        }
        holder.setPrepTimeText(String.format("%s mins", recipe.getRecipe().getTotalTime()));
        holder.setRecipeTypes(recipe.getRecipe().getSource());
        holder.setDescription(recipe.getRecipe().getHealthLabelString());
    }


    @Override
    public int getItemCount() {
        return mRecipes.size();
    }


    public void addToScrollable(List<RecipeWrapper> newRecipes){
        mRecipes.addAll(newRecipes);
        notifyDataSetChanged();
    }
}
