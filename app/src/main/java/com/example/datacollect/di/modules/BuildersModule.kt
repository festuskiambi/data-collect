package com.example.datacollect.di.modules

import com.example.datacollect.adduser.ui.AddUserActivity
import com.example.datacollect.listUsers.ui.ListUsersActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Festus Kiambi on 6/17/19.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contibuteListUsersActivity(): ListUsersActivity

    @ContributesAndroidInjector
    abstract fun contibuteAddUserActivity(): AddUserActivity

}