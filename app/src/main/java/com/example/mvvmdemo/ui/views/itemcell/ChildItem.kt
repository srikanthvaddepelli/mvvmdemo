package com.example.mvvmdemo.ui.views.itemcell

import android.view.View
import android.widget.TextView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.domain.model.SMSModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

open class ChildItem(
    val smsModel: SMSModel,
 ) : AbstractItem<ChildItem.ViewHolder>() {


    /** defines the type defining this item. must be unique. preferably an id */
    override val type: Int
        get() = 0

    /** defines the layout which will be used for this item in the list */
    override val layoutRes: Int
        get() = R.layout.sms_cell

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : FastAdapter.ViewHolder<ChildItem>(itemView) {
        private val senderNameTv: TextView = itemView.findViewById(R.id.sender_name_tv)
        private val messageTv: TextView = itemView.findViewById(R.id.message_tv)
        override fun bindView(item: ChildItem, payloads: List<Any>) {
            senderNameTv.text = item.smsModel.address
            messageTv.text = item.smsModel.body
        }

        override fun unbindView(item: ChildItem) {
            senderNameTv.text = null
            messageTv.text = null
        }
    }
}