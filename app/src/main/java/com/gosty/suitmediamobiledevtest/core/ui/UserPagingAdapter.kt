package com.gosty.suitmediamobiledevtest.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gosty.suitmediamobiledevtest.R
import com.gosty.suitmediamobiledevtest.core.domain.models.UserModel
import com.gosty.suitmediamobiledevtest.databinding.ItemUserCardBinding

/**
 * Paging adapter for user data
 */
class UserPagingAdapter :
    PagingDataAdapter<UserModel, UserPagingAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    /**
     * callback for card is clicked
     */
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
            holder.binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(user)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: UserModel)
    }

    /**
     * Setup view
     */
    class MyViewHolder(val binding: ItemUserCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserModel) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_broken_image)
                    .into(ivAvatar)

                tvFullname.text = itemView.context.getString(R.string.fullname, user.firstName, user.lastName)
                tvEmail.text = user.email
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<UserModel> =
            object : DiffUtil.ItemCallback<UserModel>() {
                override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
                    return oldItem == newItem
                }
            }
    }
}