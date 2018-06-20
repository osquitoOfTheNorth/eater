package oscar.com.eater.Presenter

import android.app.Activity
import android.view.View
import oscar.com.eater.BaseActivity
import oscar.com.eater.Fragments.RecipeDetailsFragment
import oscar.com.eater.Pojo.RecipeWrapper

class RecipePresenter {

    fun onClick(v: View, wrapper : RecipeWrapper) {
        val activityCompat = v.context as Activity
        if (activityCompat is BaseActivity) {
            activityCompat.addFragmentToStack(RecipeDetailsFragment.getInstance(wrapper))
        }
    }
}