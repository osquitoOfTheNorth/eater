package oscar.com.eater.Adapter;

import android.content.Context;
import android.database.DataSetObserver;
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


    private Ingredient[] mItems;
    private final int HEADER_VIEW_TYPE = 0;
    private final int NORMAL_VIEW_TYPE = 1;
    public RecipeIngredientsAdapter(Ingredient[] instructions) {
        mItems = instructions;
    }



    @Override
    public long getItemId(int i) {
        return getItemOffsetByHeaderView(i).hashCode();
    }
    /*
        Ingredient analyzedInstruction = getItemOffsetByHeaderView(i);
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            switch (getItemViewType(i)){
                case HEADER_VIEW_TYPE:
                    view = inflater.inflate(R.layout.recipe_instructions_view_holder_header, viewGroup, false);
                    break;
                default:
                    view = inflater.inflate(R.layout.recipe_instructions_view_holder, viewGroup, false);
                    break;
            }
        }
        switch (getItemViewType(i)){
            case HEADER_VIEW_TYPE:
                setHeaderView(view, analyzedInstruction);
                break;
            default:
                setNormalView(view, analyzedInstruction);
                break;

        }
        return view;
    } */

    @Override
    public int getItemViewType(int i) {
        return i == 0 ? HEADER_VIEW_TYPE : NORMAL_VIEW_TYPE;
    }



    private void setHeaderView(View view, Direction analyzedInstruction){
        TextView header = (TextView) view.findViewById(R.id.header_view);
        header.setText("DIRECTIONS");
    }


    private void setNormalView(View view, Direction analyzedInstruction){
        TextView instruction = (TextView) view.findViewById(R.id.instruction_text_view);
        TextView stepNumber = (TextView) view.findViewById(R.id.instruction_step_number);
        instruction.setText(analyzedInstruction.getInstruction());
        stepNumber.setText(String.valueOf(analyzedInstruction.getInstructionStepNumber()));
    }

    private Ingredient getItemOffsetByHeaderView(int i){
        switch(i){
            case 0:
                return new Ingredient();
            default:
                return mItems[i-1];
        }
    }


    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = getItemOffsetByHeaderView(position);
        holder.bindView(ingredient);
    }

    @Override
    public int getItemCount() {
        return mItems.length + 1;
    }


}

