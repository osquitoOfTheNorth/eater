package oscar.com.eater.Fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import oscar.com.eater.Enum.PickerType
import oscar.com.eater.Interfaces.DateTimePickerClickListener
import oscar.com.eater.R
import oscar.com.eater.ViewModelFactories.ViewModelFactory
import oscar.com.eater.ViewModels.DateTimePickerViewModel
import oscar.com.eater.databinding.DateTimePickerDialogBinding

class DateTimePickerDialogFragment : DialogFragment(), DateTimePickerClickListener{


    companion object {
        fun getInstance() : DateTimePickerDialogFragment{
            return DateTimePickerDialogFragment()
        }

        val tag = "DateTimePickerDialogFragment"
    }
    private lateinit var viewModel : DateTimePickerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO: Provide factory instead ?
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(DateTimePickerViewModel::class.java)
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<DateTimePickerDialogBinding>(inflater,R.layout.date_time_picker_dialog,
                container,false)
        dataBinding.clickListener = viewModel
        return dataBinding.root
    }

    override fun onPickerButtonClicked(type: PickerType) {
        when(type){
            PickerType.DATE -> { DatePickerDialog(context).show()}
            PickerType.TIME -> { TimePickerDialog(context, null, 1,1,false).show()}
        }
    }
}