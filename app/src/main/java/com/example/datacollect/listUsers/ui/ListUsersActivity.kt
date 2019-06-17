package com.example.datacollect.listUsers.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.datacollect.R
import com.example.datacollect.adduser.ui.AddUserActivity
import com.example.datacollect.listUsers.viewmodel.ListUserViewModel
import com.example.datacollect.listUsers.viewmodel.ListUserViewModelFactory
import kotlinx.android.synthetic.main.activity_list_users.*
import javax.inject.Inject

class ListUsersActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ListUserViewModelFactory
    private lateinit var viewModel: ListUserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_users)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ListUserViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        viewModel.handleEvent(ListUserEvent.OnStart)
        setUpButtons()
        observeViewModels()
    }

    private fun observeViewModels() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setUpButtons() {
        fab_add_user.setOnClickListener {
            startAddUserFeature()
        }
    }

    private fun startAddUserFeature() {
        val i = Intent(this@ListUsersActivity, AddUserActivity::class.java)
        startActivity(i)
    }


}
