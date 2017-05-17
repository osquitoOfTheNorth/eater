package oscar.com.eater.Adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import io.reactivex.Observable;
import oscar.com.eater.Observables.ObservableImageRequest;
import oscar.com.eater.Observers.ObserverImageDownloader;
import oscar.com.eater.Pojo.Ingredient;
import oscar.com.eater.Pojo.Instruction;
import oscar.com.eater.R;

/**
 * Created by omenji on 4/2/17.
 */

public class RecipeInstructionsAdapter implements ListAdapter {


    private List<Instruction> mItems;
    private Context mContext;

    public RecipeInstructionsAdapter(Context context, List<Instruction> instructions) {
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
        return mItems.get(i).getStepNumber();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Instruction analyzedInstruction = mItems.get(i);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.recipe_instructions_view_holder,viewGroup,false);
        TextView stepNumber = (TextView) v.findViewById(R.id.instruction_image_view);
        TextView instruction = (TextView) v.findViewById(R.id.instruction_text_view);
        stepNumber.setText(String.valueOf(analyzedInstruction.getStepNumber()));
        instruction.setText(analyzedInstruction.getInstructionDetail());
        return v;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return mItems.size() == 0;
    }
}

