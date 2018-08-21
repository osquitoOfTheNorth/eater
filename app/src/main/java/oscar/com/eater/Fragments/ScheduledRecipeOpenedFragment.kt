package oscar.com.eater.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import oscar.com.eater.Activities.ScheduledRecipeActivity
import oscar.com.eater.R

class ScheduledRecipeOpenedFragment : Fragment() {

    var recipeUrl : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments?.containsKey(ScheduledRecipeActivity.recipeKey) == true){
            recipeUrl = arguments?.getString(ScheduledRecipeActivity.recipeKey) ?: ""
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.scheduled_recipe_opened_fragment, container, false)
        val textView = view.findViewById<TextView>(R.id.recipe_url)
        textView.text = recipeUrl
        return view
    }




}