package oscar.com.eater.ViewModelFactories

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import oscar.com.eater.Interfaces.DateTimePickerClickListener
import oscar.com.eater.ViewModels.AccountViewModel
import oscar.com.eater.ViewModels.DateTimePickerViewModel

class ViewModelFactory(private val listener : DateTimePickerClickListener?,
                       private val app : Application,
                       private val recipeUrl : String?) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass){
            DateTimePickerViewModel::class.java -> DateTimePickerViewModel(listener!!, app, recipeUrl!!) as T
            AccountViewModel::class.java -> AccountViewModel(app) as T
            else -> DateTimePickerViewModel(listener!!, app, recipeUrl!!) as T
        }
    }
}