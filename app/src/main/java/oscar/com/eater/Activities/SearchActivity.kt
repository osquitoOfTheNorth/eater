package oscar.com.eater.Activities


import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import oscar.com.eater.ApplicationContants.searchQueryStringKey
import oscar.com.eater.BaseActivity
import oscar.com.eater.Fragments.AccountMangementFragment
import oscar.com.eater.Fragments.RecipeWallFragment
import oscar.com.eater.Fragments.SearchFragment
import oscar.com.eater.Interfaces.SearchListener
import oscar.com.eater.R

/**
 * Created by oscmenji on 2018-04-01.
 */
class SearchActivity : BaseActivity(), SearchListener {

    private lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener {
            setCurrentFragment(it.title)
            true
        }
    }
    @LayoutRes
    override fun getLayoutResId(): Int {
        return R.layout.search_activity_layout
    }

    @IdRes
    override fun getContainerId(): Int {
        return R.id.main_content
    }

    override fun createFragment(): Fragment  {
        return SearchFragment()
    }


    override fun OnSearchQuerySubmitted(searchString: String) {
        val searchBundle = Bundle()
        searchBundle.putString(searchQueryStringKey,searchString)
        val fragment = RecipeWallFragment()
        fragment.arguments = searchBundle
        addFragmentToStack(fragment, R.anim.fade_in_animation, 0)
    }

    private fun setCurrentFragment(itemPressed : CharSequence){
        val fragment : Fragment = when(itemPressed){
            getString(R.string.search) -> SearchFragment()
            getString(R.string.account) -> AccountMangementFragment()
            else -> SearchFragment()
        }
        addFragmentToStack(fragment, R.anim.fade_in_animation, 0)

    }


}