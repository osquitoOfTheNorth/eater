package oscar.com.eater.Activities;

import android.app.Fragment;
import oscar.com.eater.BaseActivity;
import oscar.com.eater.Fragments.RecipeWallFragment;

/**
 * Created by omenji on 3/16/17.
 */

public class RecipeWallActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return new RecipeWallFragment();
    }

}
