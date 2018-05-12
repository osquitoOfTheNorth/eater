package oscar.com.eater.Holder;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import oscar.com.eater.Pojo.RecipeWrapper;
import oscar.com.eater.R;
import oscar.com.eater.Views.PrepTimeView;

/**
 * Created by omenji on 3/16/17.
 */

public class RecipeHolder extends  RecyclerView.ViewHolder{
    private ImageView recipeImageView;
    private TextView recipeTitle;
    private PrepTimeView mPrepTimeView;
    private TextView RecipeTypes;
    private TextView Description;
    public RecipeHolder(View itemView) {
        super(itemView);
        recipeImageView =  itemView.findViewById(R.id.recipe_image);
        recipeImageView.setClipToOutline(true);
        mPrepTimeView =  itemView.findViewById(R.id.prep_time);
        recipeTitle =  itemView.findViewById(R.id.title_text_box);
        RecipeTypes = itemView.findViewById(R.id.recipe_types);
        Description =  itemView.findViewById(R.id.recipe_description);

    }

    public void setRecipeTypes(String types){
        RecipeTypes.setText(types);
    }

    public void setDescription(String description){
        Description.setText(description);
    }

    public void setRecipeTitle(String title){
        recipeTitle.setText(title);
    }

    public void setRecipeImageViewPlaceHolder(Drawable d){
        recipeImageView.setImageDrawable(d);
    }

    public void setRecipeImageView(RecipeWrapper recipe){
        Picasso.get().load(recipe.getRecipe().getRecipeImage()).into(recipeImageView);
    }
    public void setPrepTimeText(String text){
        mPrepTimeView.setVisibility(View.VISIBLE);
        mPrepTimeView.setText(text);
    }
}
