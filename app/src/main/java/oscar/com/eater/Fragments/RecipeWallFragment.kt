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
    private val loadingIconDrawable: Drawable?
        get() {
            loadingiconID++
            return if (loadingiconID % 2 == 0) activity?.getDrawable(R.drawable.loading_image1) else activity?.getDrawable(R.drawable.loading_image2)
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.recipe_wall_fragment, container, false)
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment_recipe_recycler_view_holder.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        fragment_recipe_recycler_view_holder.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                //Prefetch new entries when we are 5 away from the end of the list
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.itemCount - 5 && !loadingMore) {
                    loadingMore = true
                    getRecipesForSearchQuery(true)
                }
            }
        })
        recipeWallViewModel = ViewModelProviders.of(this).get(RecipeWallViewModel::class.java)
        val adapter = RecipeViewAdapter(ArrayList())
        fragment_recipe_recycler_view_holder.adapter = adapter

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

        extractSearchQuery()
        showLoadingAnimation()
        getRecipesForSearchQuery(false)
    }

    private fun getRecipesForSearchQuery(getMore : Boolean) {
        recipeWallViewModel?.getRecipesForQueryString(searchQuery,getMore)?.observe(this, Observer { list ->
            list?.let {
                if(!getMore){
                    loading_icon.animation = null
                    loading_icon.visibility = View.GONE
                    fragment_recipe_recycler_view_holder.startAnimation(AnimationUtils.loadAnimation(activity,R.anim.fade_in_animation))
                } else {
                    loadingMore = false
                }
                (fragment_recipe_recycler_view_holder.adapter as RecipeViewAdapter).addToScrollable(list)
            }
        })
    }

    private fun extractSearchQuery() {
        searchQuery = arguments?.getString(ApplicationContants.searchQueryStringKey)!!
    }

    private fun showLoadingAnimation(){
        loading_icon.visibility = View.VISIBLE
        loading_icon.setImageDrawable(loadingIconDrawable)
        loading_icon.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate_animation))
        fragment_recipe_recycler_view_holder.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.fade_out_animation))
    }

    private fun hideLoadingAnimation(){
        loading_icon.visibility = View.GONE
        loading_icon.clearAnimation()
    }

}
