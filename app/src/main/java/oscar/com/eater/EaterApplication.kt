package oscar.com.eater

import android.app.Application
import com.google.firebase.FirebaseApp

class EaterApplication : Application(){


    companion object {
        private var app : Application? = null
        fun getApplication() : Application{
            return app!!
        }
    }
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        app = this
    }
}