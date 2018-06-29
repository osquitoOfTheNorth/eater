package oscar.com.eater.Adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import oscar.com.eater.Holder.IngredientViewHolder;
import oscar.com.eater.Pojo.Direction;
import oscar.com.eater.Pojo.Ingredient;
import oscar.com.eater.Pojo.Instruction;
import oscar.com.eater.R;

/**
 * Created by omenji on 4/2/17.
 */

public class RecipeIngredientsAdapter extends RecyclerView.Adapter<IngredientViewHolder> {


    private String[] mItems;
    private final int HEADER_VIEW_TYPE = 0;
    private final int NORMAL_VIEW_TYPE = 1;
    public RecipeIngredientsAdapter(String[] instructions) {
        mItems = instructions;
    }

    @Override
    public int getItemViewType(int i) {
        return i == 0 ? HEADER_VIEW_TYPE : NORMAL_VIEW_TYPE;
    }


    private String getItemOffsetByHeaderView(int i){
        switch(i){
            case 0:
                return "Ingredients";
            default:
                return mItems[i-1];
        }
    }


    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case HEADER_VIEW_TYPE:
                view = DataBindingUtil.inflate(inflater,R.layout.recipe_instructions_view_holder_header, parent, false);
                break;
            default:
                view = DataBindingUtil.inflate(inflater, R.layout.recipe_instructions_view_holder, parent, false);
                break;
        }
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        String ingredient = getItemOffsetByHeaderView(position);
        Ingredient i = new Ingredient(ingredient,position);
        holder.bindView(i);
    }

    @Override
    public int getItemCount() {
        return mItems.length + 1;
    }


}

