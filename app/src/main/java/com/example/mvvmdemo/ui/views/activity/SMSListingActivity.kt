package com.example.mvvmdemo.ui.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.domain.model.GroupModel
import com.example.mvvmdemo.domain.model.SMSModel
import com.example.mvvmdemo.ui.StateValue
import com.example.mvvmdemo.ui.viewmodels.SMSListingActivityViewModel
import com.example.mvvmdemo.ui.views.itemcell.ChildItem
import com.example.mvvmdemo.ui.views.itemcell.ParentItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.scroll.EndlessRecyclerOnScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class SMSListingActivity : AppCompatActivity() {

    private var lastChildItem: ChildItem? = null

    private val detailViewModel: SMSListingActivityViewModel by viewModel()
    private var smsRecyclerview: RecyclerView? = null

    private val adapterList = ItemAdapter<ParentItem>()
    private val fastAdapter = FastAdapter.with(adapterList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smslisting)
        initView()
        getSMS()
        observeInboxSMS()
    }

    private fun handleList(newList: List<SMSModel>) {
        val groupedList = newList.groupBy { it.hoursAgo }
        val parentItems = groupedList.entries.map {
            GroupModel(
                it.value[0].hoursAgo,
                it.value.toMutableList()
            )
        }

        val list = mutableListOf<ParentItem>()

        for (i in parentItems.indices) {
            val item = parentItems[i]
            if ((i == 0 && lastChildItem != null && lastChildItem!!.smsModel.hoursAgo == item.hoursAgo)) {
                val parentItem = adapterList.getAdapterItem(i)
                smsRecyclerview?.let {
                    parentItem.getViewHolder(it).addItems(item.list)
                }
            } else {
                list.add(ParentItem(item, item.list))
            }
            if (i == parentItems.size - 1) {
                lastChildItem = item.list.map { ChildItem(it) }[item.list.size - 1]
            }
        }
        adapterList.add(list)

    }

    private fun initView() {
        fastAdapter.setHasStableIds(true)
        smsRecyclerview = findViewById(R.id.smsRecyclerview)
        val layoutManager = LinearLayoutManager(this)
        smsRecyclerview?.layoutManager = layoutManager
        smsRecyclerview?.adapter = fastAdapter
        val pageListener = object : EndlessRecyclerOnScrollListener(5) {
            override fun onLoadMore(currentPage: Int) {
                loadMoreSMS()
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        }
        smsRecyclerview?.addOnScrollListener(pageListener)
    }

    private fun observeInboxSMS() {
        lifecycleScope.launchWhenStarted {

            detailViewModel.smsList.collect { it ->
                when (it) {
                    is StateValue.Success -> {
                        it.value?.let {
                            handleList(it)
                        }
                    }
                    is StateValue.Failure -> {
                        Toast.makeText(
                            this@SMSListingActivity,
                            "Failed.." + it.throwable,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is StateValue.Loading -> {
                        Toast.makeText(this@SMSListingActivity, "Loading..", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }

    }

    private fun getSMS() {
        detailViewModel.fetchSMS()
    }

    private fun loadMoreSMS() {
        smsRecyclerview?.adapter?.itemCount?.let { getSMS() }
    }

}