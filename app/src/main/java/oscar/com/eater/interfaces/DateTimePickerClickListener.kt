package oscar.com.eater.interfaces

import oscar.com.eater.enum.PickerType

interface DateTimePickerClickListener {

    fun onPickerButtonClicked(type : PickerType)

    fun onScheduled()
}