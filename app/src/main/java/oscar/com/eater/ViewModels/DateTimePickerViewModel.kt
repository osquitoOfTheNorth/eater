package oscar.com.eater.ViewModels

import android.arch.lifecycle.ViewModel
import oscar.com.eater.Enum.PickerType
import oscar.com.eater.Interfaces.DateTimePickerClickListener

class DateTimePickerViewModel(private val listener : DateTimePickerClickListener) : ViewModel() {

    fun onSelectDateClicked(){
        listener.onPickerButtonClicked(PickerType.DATE)
    }

    fun onSelectTimeClicked(){
        listener.onPickerButtonClicked(PickerType.TIME)
    }


}