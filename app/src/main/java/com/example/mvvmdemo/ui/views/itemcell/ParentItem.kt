package com.example.mvvmdemo.ui.views.itemcell

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.domain.model.GroupModel
import com.example.mvvmdemo.domain.model.SMSModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem

open class ParentItem(
    var groupModel: GroupModel,
    var smsModelList: MutableList<SMSModel>
) : AbstractItem<ParentItem.ViewHolder>() {


    /** defines the type defining this item. must be unique. preferably an id */
    override val type: Int
        get() = R.id.root

    /** defines the layout which will be used for this item in the list */
    override val layoutRes: Int
        get() = R.layout.sms_cell_header

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View) : FastAdapter.ViewHolder<ParentItem>(itemView) {
        private val labelTv: TextView = itemView.findViewById(R.id.label_tv)
        private val smsRecyclerview: RecyclerView = itemView.findViewById(R.id.sms_recyclerview)

        fun addItems( newSmsModelList: MutableList<SMSModel>){
            smsModelList.addAll(newSmsModelList)
        }

        override fun bindView(item: ParentItem, payloads: List<Any>) {
            val adapterList = ItemAdapter<ChildItem>()
            val fastAdapter = FastAdapter.with(adapterList)
            fastAdapter.setHasStableIds(true)

            labelTv.text =  item.groupModel.hoursAgo.toString()+" hr ago"
            val layoutManager = LinearLayoutManager(smsRecyclerview.context)
            smsRecyclerview.layoutManager = layoutManager
            smsRecyclerview.adapter = fastAdapter
            adapterList.add(smsModelList.map { ChildItem(it) })
        }

        override fun unbindView(item: ParentItem) {
            labelTv.text = null
            smsRecyclerview.adapter = null
        }
    }
}