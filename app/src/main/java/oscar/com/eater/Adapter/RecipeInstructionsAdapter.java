package oscar.com.eater.Adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import oscar.com.eater.Pojo.Direction;
import oscar.com.eater.Pojo.Instruction;
import oscar.com.eater.R;

/**
 * Created by omenji on 4/2/17.
 */

public class RecipeInstructionsAdapter implements ListAdapter {


    private List<Direction> mItems;
    private final int HEADER_VIEW_TYPE = 0;
    private final int NORMAL_VIEW_TYPE = 1;
    private final int VIEW_TYPE_COUNT = 2;
    private Context mContext;

    public RecipeInstructionsAdapter(Context context, List<Direction> instructions) {
        mItems = instructions;
        mContext = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int i) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).getInstructionStepNumber();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Direction analyzedInstruction = mItems.get(i);
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
    }

    @Override
    public int getItemViewType(int i) {
        return i == 0 ? HEADER_VIEW_TYPE : NORMAL_VIEW_TYPE;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public boolean isEmpty() {
        return mItems.size() == 0;
    }


    private void setHeaderView(View view, Direction analyzedInstruction){
        TextView header = (TextView) view.findViewById(R.id.header_view);
        header.setText("DIRECTIONS");
    }


    private void setNormalView(View view, Direction analyzedInstruction){
        TextView instruction = (TextView) view.findViewById(R.id.instruction_text_view);
        TextView stepNumber = (TextView) view.findViewById(R.id.instruction_step_number);
        instruction.setText(analyzedInstruction.getInstruction());
        stepNumber.setText(analyzedInstruction.getInstructionStepNumber());
    }
}

