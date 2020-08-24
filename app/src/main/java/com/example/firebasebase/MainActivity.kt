package com.example.firebasebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import com.example.firebasebase.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics

import java.util.*

class MainActivity : AppCompatActivity(), Observer, LifecycleOwner {
    var firebaseAnalytics: FirebaseAnalytics? = null
    lateinit var activityMainActivity: ActivityMainBinding
    var viewModel: MainViewModel = MainViewModel()
    var firebaseCrashlytics: FirebaseCrashlytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainActivity = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainActivity.viewModel = viewModel
        viewModel.addObserver(this)
        lifecycle.addObserver(viewModel)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

    }


    override fun update(o: Observable?, arg: Any?) {
        if (arg is View) {

            when (arg.id) {
                R.id.btn1 -> {
                    val param = Bundle()
                    param.putString(FirebaseAnalytics.Param.ITEM_ID, "2")
                    param.putString(FirebaseAnalytics.Param.ITEM_NAME, "selva")
                    param.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
                    firebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SELECT_ITEM, param)
                    firebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, param)
                }

                R.id.btn2 -> {
//                    throw RuntimeException("Test Crash")// Force a crash
                    FirebaseCrashlytics.getInstance().setUserId("12345")
                    FirebaseCrashlytics.getInstance().log("Higgs-Boson detected! Bailing out")
                }
            }
        }
    }
}
