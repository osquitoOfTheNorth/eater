package oscar.com.eater.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import oscar.com.eater.Pojo.ObservableString
import oscar.com.eater.R

class AccountViewModel(private val app : Application) : AndroidViewModel(app) {
    private val user = FirebaseAuth.getInstance().currentUser
    val helloString = app.getString(R.string.welcome, user?.displayName)



}