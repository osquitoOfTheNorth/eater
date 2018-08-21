package oscar.com.eater.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.Log

class ScheduledRecipeActivity : AppCompatActivity(){

    companion object {
        val SCHEDULED_RECIPE_OPENED_REQUEST_CODE = 1234
        val recipeKey = "SCHEDULED_RECIPE_URL"
    }

    override fun onNewIntent(intent: Intent?) {
        Log.e("HOLY SHIT", "IT WORKS!!!")
    }
}