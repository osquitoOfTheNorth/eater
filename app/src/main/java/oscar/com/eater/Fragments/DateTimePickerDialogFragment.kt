package oscar.com.eater.Fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import oscar.com.eater.EaterApplication
import oscar.com.eater.Enum.PickerType
import oscar.com.eater.Interfaces.DateTimePickerClickListener
import oscar.com.eater.R
import oscar.com.eater.ViewModelFactories.ViewModelFactory
import oscar.com.eater.ViewModels.DateTimePickerViewModel
import oscar.com.eater.databinding.DateTimePickerDialogBinding
import java.util.*

class DateTimePickerDialogFragment : DialogFragment(), DateTimePickerClickListener{


    companion object {
        fun getInstance(recipeUrl : String) : DateTimePickerDialogFragment{
            val args = Bundle()
            args.putSerializable(recipeUrlKey, recipeUrl)
            val fragment = DateTimePickerDialogFragment()
            fragment.arguments = args
            return fragment
        }
        val recipeUrlKey = "RECIPE_URL"
        val tag = "DateTimePickerDialogFragment"
    }

    private lateinit var viewModel : DateTimePickerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO: Provide factory instead ?
        val recipeUrl = if (arguments?.containsKey(recipeUrlKey) == true) arguments?.getSerializable(recipeUrlKey) as String else ""
        viewModel = ViewModelProviders.of(this,
                ViewModelFactory(this, EaterApplication.getApplication(),recipeUrl)).get(DateTimePickerViewModel::class.java)
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding = DataBindingUtil.inflate<DateTimePickerDialogBinding>(inflater,R.layout.date_time_picker_dialog,
                container,false)
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    override fun onPickerButtonClicked(type: PickerType) {
        val today = Calendar.getInstance()
        val thisYear = today.get(Calendar.YEAR)
        val thisMonth = today.get(Calendar.MONTH)
        val thisDayOfMonth = today.get(Calendar.DAY_OF_MONTH)
        val currentHour = today.get(Calendar.HOUR)
        val currentMinute = today.get(Calendar.MINUTE)
        when(type){
            PickerType.DATE -> { DatePickerDialog(context, viewModel, thisYear,thisMonth,thisDayOfMonth).show()}
            PickerType.TIME -> { TimePickerDialog(context, viewModel, currentHour,currentMinute,false).show()}
        }
    }

    override fun onScheduled() {
        this.dismiss()
        val toastText = context?.getString(R.string.scheduled_toast_notification)
        Toast.makeText(context,toastText,Toast.LENGTH_LONG).show()
    }
}