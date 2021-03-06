package oscar.com.eater.viewmodels

import android.app.*
import android.app.PendingIntent.FLAG_CANCEL_CURRENT
import android.arch.lifecycle.AndroidViewModel
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.widget.DatePicker
import android.widget.TimePicker
import oscar.com.eater.enum.PickerType
import oscar.com.eater.interfaces.DateTimePickerClickListener
import oscar.com.eater.receivers.ScheduledActionReceiver
import java.text.SimpleDateFormat
import java.util.*

class DateTimePickerViewModel(private val listener : DateTimePickerClickListener,
                              private val app : Application,
                              private val recipeUrl : String) : AndroidViewModel(app),
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private var date : Calendar = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("EEE, MMM dd, yyyy")
    private val timeFormatter = SimpleDateFormat("h:mm a")
    val humanReadableDate : ObservableField<String> = ObservableField(dateFormatter.format(date.time))
    val humanReadableTime : ObservableField<String> = ObservableField(timeFormatter.format(date.time))
    fun onSelectDateClicked(){
        listener.onPickerButtonClicked(PickerType.DATE)
    }

    fun onSelectTimeClicked(){
        listener.onPickerButtonClicked(PickerType.TIME)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date.set(Calendar.YEAR,year)
        date.set(Calendar.MONTH,month)
        date.set(Calendar.DAY_OF_MONTH,dayOfMonth)
        humanReadableDate.set(dateFormatter.format(date.time))
    }

    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
        date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH),hour,minute)
        humanReadableTime.set(timeFormatter.format(date.time))
    }

    fun onScheduleClick(){
        val alarmManager = app.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = getPendingIntent()
        alarmManager.setExact(AlarmManager.RTC, date.timeInMillis, pendingIntent)
        listener.onScheduled()
    }

    private fun getPendingIntent() : PendingIntent{
        val intent = Intent()
        intent.putExtra(ScheduledActionReceiver.SCHEDULED_RECIPE_WEB_URL_INTENT_KEY, recipeUrl)
        intent.setClass(app, ScheduledActionReceiver::class.java)
        return PendingIntent.getBroadcast(app, ScheduledActionReceiver.SCHEDULED_RECIPE_RECEIVER_CODE, intent, FLAG_CANCEL_CURRENT)
    }
}