package oscar.com.eater.Activities


import android.os.Bundle
import android.support.v4.app.Fragment
import oscar.com.eater.ApplicationContants.searchQueryStringKey
import oscar.com.eater.BaseActivity
import oscar.com.eater.Fragments.RecipeWallFragment
import oscar.com.eater.Fragments.SearchFragment
import oscar.com.eater.Interfaces.SearchListener
import oscar.com.eater.R

/**
 * Created by oscmenji on 2018-04-01.
 */
class SearchActivity : BaseActivity(), SearchListener {

    override fun createFragment(): Fragment  {
        return SearchFragment()
    }


    override fun OnSearchQuerySubmitted(searchString: String) {
        val searchBundle = Bundle()
        searchBundle.putString(searchQueryStringKey,searchString)
        val fragment = RecipeWallFragment()
        fragment.arguments = searchBundle
        addFragmentToStack(fragment, R.anim.fade_in_animation, R.anim.fade_out_animation)
    }
}