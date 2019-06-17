package com.example.datacollect.di.components

import com.example.datacollect.DataCollectApplication
import com.example.datacollect.di.modules.AppModule
import com.example.datacollect.di.modules.BuildersModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Festus Kiambi on 6/17/19.
 */
@Singleton
@Component(
    modules = [AppModule::class, BuildersModule::class]
)
interface AppComponent {

    fun inject(app: DataCollectApplication)

}