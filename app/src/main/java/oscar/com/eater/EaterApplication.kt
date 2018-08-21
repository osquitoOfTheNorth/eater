package oscar.com.eater

import android.app.Application

class EaterApplication : Application(){


    companion object {
        private var app : Application? = null
        fun getApplication() : Application{
            return app!!
        }
    }
    override fun onCreate() {
        super.onCreate()
        app = this
    }
}