package com.example.datacollect.listUsers.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datacollect.R
import com.example.datacollect.adduser.ui.AddUserActivity
import com.example.datacollect.listUsers.viewmodel.ListUserViewModel
import com.example.datacollect.listUsers.viewmodel.ListUserViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_list_users.*
import javax.inject.Inject

class ListUsersActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ListUserViewModelFactory
    private lateinit var viewModel: ListUserViewModel

    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_users)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ListUserViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        Toast.makeText(this, "click on '+' to add a new user" , Toast.LENGTH_SHORT).show()

        viewModel.handleEvent(ListUserEvent.OnStart)
        setUpButtons()
        setUpAdapter()
        observeViewModels()
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(ListUserEvent.OnStart)
    }

    private fun setUpAdapter() {
        adapter = UsersAdapter()
        adapter.event.observe(this,
            Observer { viewModel.handleEvent(it) })
        rv_users.adapter = adapter
    }

    private fun observeViewModels() {
        rv_users.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewModel.usersList.observe(
            this,
            Observer {userList ->
                adapter.submitList(userList)
            }
        )
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
