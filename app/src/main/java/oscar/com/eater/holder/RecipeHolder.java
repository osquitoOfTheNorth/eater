package oscar.com.eater.holder;
import android.support.v7.widget.RecyclerView;
import oscar.com.eater.pojo.RecipeWrapper;
import oscar.com.eater.presenter.RecipePresenter;
import oscar.com.eater.databinding.RecipeViewHolderBinding;

/**
 * Created by omenji on 3/16/17.
 */

public class RecipeHolder extends  RecyclerView.ViewHolder {
    private RecipeViewHolderBinding binding;
    public RecipeHolder(RecipeViewHolderBinding view) {
        super(view.getRoot());
        binding = view;
    }

    public void setItem(RecipeWrapper wrapper){
        binding.setUser(wrapper);
        binding.setClickListener(new RecipePresenter());
        binding.executePendingBindings();
    }
}
