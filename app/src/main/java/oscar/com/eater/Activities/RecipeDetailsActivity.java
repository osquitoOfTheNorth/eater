package oscar.com.eater.Activities;

import android.app.Fragment;
import android.os.Bundle;

import oscar.com.eater.BaseActivity;
import oscar.com.eater.Fragments.RecipeDetailsFragment;
import oscar.com.eater.R;


/**
 * Created by omenji on 3/29/17.
 */

public class RecipeDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in_animation, R.anim.fade_out_animation);
    }

    @Override
    protected Fragment createFragment() {
        return new RecipeDetailsFragment();
    }


}
