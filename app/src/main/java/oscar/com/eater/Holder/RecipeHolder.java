package oscar.com.eater.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import oscar.com.eater.R;
import oscar.com.eater.Views.PrepTimeView;

/**
 * Created by omenji on 3/16/17.
 */

public class RecipeHolder extends  RecyclerView.ViewHolder{
    public ImageView recipeImageView;
    public TextView recipeTitle;
    public PrepTimeView mPrepTimeView;
    public ImageView isVeganImage;
    public ImageView isVegetarianImage;
    public ImageView isCheapImage;
    public TextView sourceName;

    public RecipeHolder(View itemView) {
        super(itemView);
        recipeImageView = (ImageView) itemView.findViewById(R.id.recipe_image);
        recipeImageView.setClipToOutline(true);
        mPrepTimeView = (PrepTimeView) itemView.findViewById(R.id.prep_time);
        recipeTitle = (TextView) itemView.findViewById(R.id.title_text_box);
        isCheapImage = (ImageView) itemView.findViewById(R.id.is_cheap_icon);
        isVegetarianImage = (ImageView) itemView.findViewById(R.id.is_vegetarian_icon);
        isVeganImage = (ImageView) itemView.findViewById(R.id.is_vegan_icon);
        sourceName = (TextView) itemView.findViewById(R.id.source_name);
    }
}
