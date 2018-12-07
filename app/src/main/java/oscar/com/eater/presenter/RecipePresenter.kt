package oscar.com.eater.presenter

import android.app.Activity
import android.view.View
import oscar.com.eater.BaseActivity
import oscar.com.eater.fragments.RecipeDetailsFragment
import oscar.com.eater.pojo.RecipeWrapper

class RecipePresenter {

    fun onClick(v: View, wrapper : RecipeWrapper) {
        val activityCompat = v.context as Activity
        if (activityCompat is BaseActivity) {
            activityCompat.addFragmentToStack(RecipeDetailsFragment.getInstance(wrapper))
        }
    }
}