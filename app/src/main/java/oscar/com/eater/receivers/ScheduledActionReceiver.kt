package oscar.com.eater.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import oscar.com.eater.activities.ScheduledRecipeActivity

class ScheduledActionReceiver : BroadcastReceiver(){


    companion object {
        val SCHEDULED_RECIPE_RECEIVER_CODE = 1
        val SCHEDULED_RECIPE_WEB_URL_INTENT_KEY = "SCHEDULED_RECIPE_WEB_URL_INTENT_KEY"
    }
    override fun onReceive(context: Context?, intent: Intent?) {
        val activityIntent = Intent()
        activityIntent.setClass(context,ScheduledRecipeActivity::class.java)
        activityIntent.putExtra(ScheduledRecipeActivity.recipeKey,intent?.getStringExtra(SCHEDULED_RECIPE_WEB_URL_INTENT_KEY))
        context?.startActivity(activityIntent)
    }
}