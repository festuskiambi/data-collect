package com.example.datacollect

import android.app.Activity
import android.app.Application
import com.example.datacollect.di.components.DaggerAppComponent
import com.example.datacollect.di.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by Festus Kiambi on 6/17/19.
 */
class DataCollectApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>  = activityInjector
}