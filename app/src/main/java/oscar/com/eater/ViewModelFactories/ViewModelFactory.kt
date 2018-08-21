package oscar.com.eater.ViewModelFactories

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import oscar.com.eater.Interfaces.DateTimePickerClickListener
import oscar.com.eater.ViewModels.DateTimePickerViewModel

class ViewModelFactory(private val listener : DateTimePickerClickListener,
                       private val app : Application,
                       private val recipeUrl : String) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DateTimePickerViewModel(listener, app, recipeUrl) as T
    }
}