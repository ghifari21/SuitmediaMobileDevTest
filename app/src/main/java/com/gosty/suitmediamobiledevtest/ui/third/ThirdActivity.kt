package com.gosty.suitmediamobiledevtest.ui.third

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gosty.suitmediamobiledevtest.R
import com.gosty.suitmediamobiledevtest.core.domain.models.UserModel
import com.gosty.suitmediamobiledevtest.core.ui.LoadingStateAdapter
import com.gosty.suitmediamobiledevtest.core.ui.UserPagingAdapter
import com.gosty.suitmediamobiledevtest.databinding.ActivityThirdBinding
import com.gosty.suitmediamobiledevtest.ui.second.SecondActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private val viewModel: ThirdViewModel by viewModels()
    private val pagingAdapter = UserPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        /**
         * setup action bar title and up button
         */
        supportActionBar?.apply {
            title = getString(R.string.third_screen)
            setDisplayHomeAsUpEnabled(true)
        }

        /**
         * setting recycler view with paging adapter
         */
        val layoutManager = LinearLayoutManager(this@ThirdActivity)
        val itemDecoration = DividerItemDecoration(this@ThirdActivity, layoutManager.orientation)
        binding.apply {
            rvUsers.layoutManager = layoutManager
            rvUsers.setHasFixedSize(true)
            rvUsers.addItemDecoration(itemDecoration)
            rvUsers.adapter = pagingAdapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    pagingAdapter.retry()
                }
            )

            /**
             * pull to refresh action
             */
            refresh.setOnRefreshListener {
                getUsers()
            }
        }

        /**
         * managing data state
         */
        pagingAdapter
            .loadStateFlow
            .asLiveData()
            .observe(this@ThirdActivity) {
                it.refresh.apply {
                    when (this) {
                        is LoadState.Loading -> {
                            loadingState(true)
                        }

                        is LoadState.NotLoading -> {
                            loadingState(false)

                            /**
                             * empty data state
                             */
                            if (pagingAdapter.itemCount == 0) {
                                emptyState(true)
                            } else {
                                emptyState(false)
                            }
                        }

                        is LoadState.Error -> {
                            loadingState(false)
                            Timber.e(error.localizedMessage)

                            Toast.makeText(
                                this@ThirdActivity,
                                error.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        getUsers()
        selectUser()
    }

    private fun getUsers() {
        viewModel.getUsers().observe(this@ThirdActivity) {
            pagingAdapter.submitData(lifecycle, it)
        }
        binding.refresh.isRefreshing = false
    }

    /**
     * select user and send the result to second activity
     */
    private fun selectUser() {
        pagingAdapter.setOnItemClickCallback(
            object : UserPagingAdapter.OnItemClickCallback {
                override fun onItemClicked(user: UserModel) {
                    val intent = Intent()
                    val full = "${user.firstName} ${user.lastName}"
                    intent.putExtra(SecondActivity.RESULT_DATA, full)
                    setResult(SecondActivity.USER_RESULT, intent)
                    finish()
                }
            }
        )
    }

    private fun loadingState(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                shimmer.visibility = View.VISIBLE
                rvUsers.visibility = View.GONE
                emptyState.visibility = View.GONE
            } else {
                shimmer.visibility = View.GONE
                rvUsers.visibility = View.VISIBLE
                emptyState.visibility = View.GONE
            }
        }
    }

    private fun emptyState(isEmpty: Boolean) {
        binding.apply {
            if (isEmpty) {
                shimmer.visibility = View.GONE
                rvUsers.visibility = View.GONE
                emptyState.visibility = View.VISIBLE
            } else {
                shimmer.visibility = View.GONE
                rvUsers.visibility = View.VISIBLE
                emptyState.visibility = View.GONE
            }
        }
    }
}