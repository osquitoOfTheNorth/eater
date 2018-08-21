package oscar.com.eater.Activities
import android.os.Bundle
import android.support.v4.app.Fragment
import oscar.com.eater.BaseActivity
import oscar.com.eater.Fragments.ScheduledRecipeOpenedFragment

class ScheduledRecipeActivity : BaseActivity(){

    companion object {
        val recipeKey = "SCHEDULED_RECIPE_URL"
    }

    var url : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        url = intent.getStringExtra(recipeKey)
        super.onCreate(savedInstanceState)
    }

    override fun createFragment(): Fragment {
        return ScheduledRecipeOpenedFragment.getInstance(url)
    }
}