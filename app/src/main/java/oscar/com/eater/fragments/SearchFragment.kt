package oscar.com.eater.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import oscar.com.eater.interfaces.SearchListener
import oscar.com.eater.R

/**
 * Created by oscmenji on 2018-04-01.
 */
class SearchFragment : Fragment() {

    private var searchListener : SearchListener? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        searchListener = context as SearchListener?
    }





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.recipe_search_view,container,false)
        val searchView = v?.findViewById<SearchView>(R.id.recipe_search_view_container)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let { searchString ->
                    searchView.clearFocus()
                    searchListener?.OnSearchQuerySubmitted(searchString)
                    return true
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
        return v!!
    }
}