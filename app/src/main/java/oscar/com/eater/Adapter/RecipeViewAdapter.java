package oscar.com.eater.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import oscar.com.eater.ApplicationContants;
import oscar.com.eater.DaggerModules.BaseActivityComponent;
import oscar.com.eater.DaggerModules.BaseActivityModule;
import oscar.com.eater.DaggerModules.DaggerBaseActivityComponent;
import oscar.com.eater.Activities.RecipeDetailsActivity;
import oscar.com.eater.Observables.ObservableImageRequest;
import oscar.com.eater.Observers.ObserverImageDownloader;
import oscar.com.eater.Pojo.Recipe;
import oscar.com.eater.R;
import oscar.com.eater.Holder.RecipeHolder;

/**
 * Created by omenji on 3/16/17.
 */

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeHolder> {

    Context mContext;
    private List<Recipe> mRecipes;
    private Context mActivity;
    private BaseActivityComponent baseActivtyComponentContextInjector;


    public RecipeViewAdapter(List<Recipe> recipes, Context baseActivityInstance){
        mRecipes = recipes;
        baseActivtyComponentContextInjector = DaggerBaseActivityComponent.builder().baseActivityModule(new BaseActivityModule(baseActivityInstance)).build();
        mContext = baseActivtyComponentContextInjector.context().getApplicationContext();
        mActivity = baseActivityInstance;
    }


    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewForViewHolder = inflater.inflate(R.layout.recipe_view_holder, parent, false);
        return new RecipeHolder(viewForViewHolder);
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {
        final Recipe recipe = mRecipes.get(position);
        holder.isVegetarianImage.setImageDrawable(getVegetarianDrawable(recipe.isVegetarian()));
        holder.isVegetarianImage.setImageAlpha(recipe.isVegetarian() ? 255 : 50);
        holder.isCheapImage.setImageDrawable(getPriceRangeDrawable(recipe.getPricePerServing()));
        holder.isVeganImage.setImageDrawable(getVeganDrawable(recipe.isVegan()));
        holder.isVeganImage.setImageAlpha(recipe.isVegan() ? 255 : 50);
        holder.recipeTitle.setText(recipe.getRecipeName());
        holder.recipeImageView.setImageDrawable(mContext.getDrawable(R.drawable.recipe_image_placeholder));
        holder.mPrepTimeView.setText(String.format("%s min",recipe.getTotalPreperationTimeInMinutes()));
        holder.sourceName.setText(recipe.getSourceName());
        Observable.create(new ObservableImageRequest(recipe.getImageSrcUrl()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ObserverImageDownloader(holder.recipeImageView, AnimationUtils.loadAnimation(mActivity.getApplicationContext(),R.anim.fade_in_animation)));
        holder.recipeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recipeDetailIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable(ApplicationContants.getRecipeSerializableKey(),recipe);
                recipeDetailIntent.putExtras(bundle);
                recipeDetailIntent.setClass(mContext,RecipeDetailsActivity.class);
                mActivity.startActivity(recipeDetailIntent);
            }
        });
    }

    private Drawable getVegetarianDrawable(boolean isVeg){
        return isVeg ? mContext.getDrawable(R.drawable.ic_vegetarian_enabled) : mContext.getDrawable(R.drawable.ic_vegetarian_disabled);
    }

    private Drawable getVeganDrawable(boolean isVegan){
        return isVegan ? mContext.getDrawable(R.drawable.ic_vegan_enabled) : mContext.getDrawable(R.drawable.ic_vegan_disabled);
    }

    private Drawable getPriceRangeDrawable(double price){
        return price<= 300 ?  mContext.getDrawable(R.drawable.ic_cheapest) : price <= 500 ?  mContext.getDrawable(R.drawable.ic_medium_priced) :
                mContext.getDrawable(R.drawable.ic_expensive);
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }


    public void addToScrollable(List<Recipe> newRecipes){
        mRecipes.addAll(newRecipes);
        notifyDataSetChanged();
    }
}
