package oscar.com.eater.ViewModels

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.widget.DatePicker
import android.widget.TimePicker
import oscar.com.eater.Enum.PickerType
import oscar.com.eater.Interfaces.DateTimePickerClickListener
import java.text.SimpleDateFormat
import java.util.*

class DateTimePickerViewModel(private val listener : DateTimePickerClickListener) : ViewModel(),
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

    fun onSchedule(){

    }
}