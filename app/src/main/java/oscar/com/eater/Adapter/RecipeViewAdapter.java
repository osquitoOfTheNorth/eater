package oscar.com.eater.Adapter;


import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;
import oscar.com.eater.Pojo.RecipeWrapper;
import oscar.com.eater.R;
import oscar.com.eater.Holder.RecipeHolder;
import oscar.com.eater.databinding.RecipeViewHolderBinding;

/**
 * Created by omenji on 3/16/17.
 */

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeHolder>{
    private List<RecipeWrapper> mRecipes;
    private RecipeViewHolderBinding binding;
    public RecipeViewAdapter(List<RecipeWrapper> recipes){
        mRecipes = recipes;
    }


    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.recipe_view_holder, parent, false);
        return new RecipeHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {
        RecipeWrapper wrapper = mRecipes.get(position);
        holder.setItem(wrapper);
    }

    @BindingAdapter("{bind:imageUrl")
    public static void setImageViewResource(ImageView imgView,String url ){
        if(url != null && url.length() > 0 ) {
            Picasso.get().load(url).into(imgView);
        }
    }


    @Override
    public int getItemCount() {
        return mRecipes.size();
    }


    public void addToScrollable(List<RecipeWrapper> newRecipes){
        mRecipes = newRecipes;
        notifyDataSetChanged();
    }

    public void setRecipes(List<RecipeWrapper> mRecipes) {
        this.mRecipes = mRecipes;
    }
}
