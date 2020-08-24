package com.example.firebasebase

import android.view.View
import androidx.lifecycle.LifecycleObserver
import java.util.*

class MainViewModel : Observable(), LifecycleObserver {

    fun onClickListener(v: View) {
        setChanged()
        notifyObservers(v)
    }
}