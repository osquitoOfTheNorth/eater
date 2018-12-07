package oscar.com.eater.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import oscar.com.eater.R

class ScheduledRecipeOpenedFragment : Fragment() {


    companion object {
        fun getInstance(url : String) : ScheduledRecipeOpenedFragment{
            val args = Bundle()
            args.putSerializable(RECIPE_URL_KEY, url)
            val fragment = ScheduledRecipeOpenedFragment()
            fragment.arguments = args
            return fragment
        }

        val RECIPE_URL_KEY = "RECIPE_URL_KEY"
    }
    var recipeUrl : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments?.containsKey(RECIPE_URL_KEY) == true){
            recipeUrl = arguments?.getString(RECIPE_URL_KEY) ?: ""
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.scheduled_recipe_opened_fragment, container, false)
        val webView = view.findViewById<WebView>(R.id.recipe_url)
        webView.loadUrl(recipeUrl)
        return view
    }




}