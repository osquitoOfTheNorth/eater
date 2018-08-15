package oscar.com.eater.Fragments

import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.recipe_wall_fragment.*
import oscar.com.eater.Adapter.RecipeViewAdapter
import oscar.com.eater.ApplicationContants
import oscar.com.eater.R
import oscar.com.eater.ViewModels.RecipeWallViewModel

/**
 * Created by omenji on 3/16/17.
 */

class RecipeWallFragment : Fragment() {
    private var loadingiconID: Int = 0
    private var searchQuery: String = ""
    private var recipeWallViewModel : RecipeWallViewModel? = null
    private var loadingMore  = false
    private var mRecipeAdapater : RecipeViewAdapter? = null
    private val loadingIconDrawable: Drawable?
        get() {
            loadingiconID++
            return if (loadingiconID % 2 == 0) activity?.getDrawable(R.drawable.loading_image1) else activity?.getDrawable(R.drawable.loading_image2)
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extractSearchQuery()
        recipeWallViewModel = ViewModelProviders.of(this).get(RecipeWallViewModel::class.java)


        recipeWallViewModel?.observeErrors()?.observe(this, Observer { errorMessage ->
            val modal = AlertDialog.Builder(context)
            modal.setMessage(errorMessage)
            modal.setTitle("Error")
            modal.show()
            hideLoadingAnimation()
        })

        recipeWallViewModel?.observeNoResultsReturned()?.observe(this, Observer { message ->
            val modal = AlertDialog.Builder(context)
            modal.setMessage(message)
            modal.setTitle("Error")
            modal.show()
            hideLoadingAnimation()
        })

        recipeWallViewModel?.observeRecipesForQueryString()?.observe(this, Observer { recipes ->
            if(recipes?.isNotEmpty()!!) {
                hideLoadingAnimation()
                loadingMore = false
                mRecipeAdapater?.addToScrollable(recipes)
            }
        })


    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.recipe_wall_fragment, container, false)
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mRecipeAdapater = RecipeViewAdapter(ArrayList())
        fragment_recipe_recycler_view_holder.adapter = mRecipeAdapater
        fragment_recipe_recycler_view_holder.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        fragment_recipe_recycler_view_holder.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                //Prefetch new entries when we are 7 away from the end of the list
                if (linearLayoutManager.findLastVisibleItemPosition() >= linearLayoutManager.itemCount - 7 && !loadingMore) {
                    getMoreRecipes()
                }
            }
        })
        showLoadingAnimation()
        getRecipesForSearchQuery()
    }

    private fun getRecipesForSearchQuery() {
        val recipes = recipeWallViewModel?.getRecipesForQueryString(searchQuery, false)
        if (recipes != null && recipes.isNotEmpty()) {
            loadingMore = false
            hideLoadingAnimation()
            mRecipeAdapater?.let {
                it.setRecipes(recipes)
                it.notifyDataSetChanged()
            }
        }
    }

    private fun getMoreRecipes(){
        loadingMore = true
        recipeWallViewModel?.getRecipesForQueryString(searchQuery, true)
    }

    private fun extractSearchQuery() {
        searchQuery = arguments?.getString(ApplicationContants.searchQueryStringKey)!!
    }

    private fun showLoadingAnimation(){
        loading_icon.visibility = View.VISIBLE
        loading_icon.setImageDrawable(loadingIconDrawable)
        loading_icon.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate_animation))
    }

    private fun hideLoadingAnimation(){
        loading_icon.visibility = View.GONE
        loading_icon.clearAnimation()
    }

}
