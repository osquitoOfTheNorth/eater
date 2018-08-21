package oscar.com.eater.Receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ScheduledRecipeReceiver : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e("HOLY SHIT I WORK AGAIN!!!", "YEAH!")
        context?.startActivity(intent)
    }
}