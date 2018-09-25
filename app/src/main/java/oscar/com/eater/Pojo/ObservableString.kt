package oscar.com.eater.Pojo

import android.databinding.BaseObservable
import android.databinding.Bindable
import oscar.com.eater.BR

class ObservableString(private var _string : String) : BaseObservable(){

    var string : String
    @Bindable
    get() = _string
    set(value) {
        _string = value
        notifyPropertyChanged(BR.string)
    }
}