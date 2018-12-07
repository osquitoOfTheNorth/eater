package oscar.com.eater.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import oscar.com.eater.R

class AccountViewModel(private val app : Application) : AndroidViewModel(app) {
    private val user = FirebaseAuth.getInstance().currentUser
    val helloString = app.getString(R.string.welcome, user?.displayName)



}