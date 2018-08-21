package oscar.com.eater.Activities
import android.support.v4.app.Fragment
import oscar.com.eater.BaseActivity
import oscar.com.eater.Fragments.ScheduledRecipeOpenedFragment

class ScheduledRecipeActivity : BaseActivity(){

    companion object {
        val recipeKey = "SCHEDULED_RECIPE_URL"
    }


    override fun createFragment(): Fragment {
        return ScheduledRecipeOpenedFragment()
    }
}