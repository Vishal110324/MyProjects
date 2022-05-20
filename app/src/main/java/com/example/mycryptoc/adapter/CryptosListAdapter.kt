package com.example.mycryptoc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mycryptoc.R
import com.example.mycryptoc.data.DataItem
import kotlinx.android.synthetic.main.item_user.view.*

class CryptosListAdapter : RecyclerView.Adapter<UsersListViewHolder>() {
    val usersList = mutableListOf<DataItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UsersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        holder.onBind(usersList[position], position)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }
}

class UsersListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun onBind(user: DataItem, position: Int) = itemView.apply {
        crypto_name.text = user.name
        crypto_price.text = user.priceUsd
        crypto_change.text = user.changePercent24Hr.format()
        crypto_sno.text = (position + 1).toString()

    }
    fun String.format(): String {
        if (!this.isNullOrBlank()) {
            val reult = String.format("%02f", this.toDouble())
            return "$reult%"
        }else{
            return ""
        }

    }
}