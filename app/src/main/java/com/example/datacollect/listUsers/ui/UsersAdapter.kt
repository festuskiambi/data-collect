package com.example.datacollect.listUsers.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.datacollect.R
import com.example.datacollect.data.User
import kotlinx.android.synthetic.main.item_user.view.*

/**
 * Created by Festus Kiambi on 6/17/19.
 */
class UsersAdapter(var event: MutableLiveData<ListUserEvent> = MutableLiveData()) :
    ListAdapter<User, UsersAdapter.ViewHolder>(UserDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            inflater.inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { user ->
            val userName = "Customer Name: ${user.firstName} ${user.lastName}"
            val idNumber = "Customer Id: ${user.idNumber}"
            val product = "product: ${user.productInfo}"
            holder.name.text = userName
            holder.idNumber.text = idNumber
            holder.productInfo.text = product
            Glide.with(holder.buldingImage)
                .load(user.buildingImageUrl)
                .into(holder.buldingImage)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.tv_customer_name
        val idNumber = view.tv_customer_id_number
        val productInfo = view.tv_product_info
        val buldingImage = view.imv_bulding_image
    }
}