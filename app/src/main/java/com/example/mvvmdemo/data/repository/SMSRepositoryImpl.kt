package com.example.mvvmdemo.data.repository

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.Telephony
import com.example.mvvmdemo.domain.Result
import com.example.mvvmdemo.domain.model.SMSModel
import com.example.mvvmdemo.domain.repository.SMSRepository
const val SMS_LIMIT_PER_QUERY = 30

class SMSRepositoryImpl: SMSRepository {

    override suspend fun getInboxSMS(context:Context,offset: Int): Result<List<SMSModel>> {
        val list = arrayListOf<SMSModel>()
        val uri = Uri.parse("content://sms/inbox")
        val cr: ContentResolver = context.contentResolver
        val projection = arrayOf(
            Telephony.TextBasedSmsColumns.THREAD_ID,
            Telephony.TextBasedSmsColumns.DATE,
            Telephony.TextBasedSmsColumns.ADDRESS,
            Telephony.TextBasedSmsColumns.BODY,
            Telephony.TextBasedSmsColumns.TYPE,
            Telephony.TextBasedSmsColumns.PERSON,
            Telephony.TextBasedSmsColumns.READ
        )
        val c = cr.query(uri, projection, null, null, Telephony.Sms.Inbox.DEFAULT_SORT_ORDER + " LIMIT " + SMS_LIMIT_PER_QUERY + " OFFSET " + offset)
        if (c!!.moveToFirst()) {
            do {
                val col = c.columnNames
                var str = ""
                for (i in col.indices) {
                    str = str + col[i] + ": " + c.getString(i) + ", "
                }
                val smsModel = SMSModel(
                    c.getInt(c.getColumnIndex(Telephony.TextBasedSmsColumns.THREAD_ID)),
                    c.getInt(c.getColumnIndex(Telephony.TextBasedSmsColumns.DATE)),
                    c.getString(c.getColumnIndex(Telephony.TextBasedSmsColumns.ADDRESS)),
                    c.getString(c.getColumnIndex(Telephony.TextBasedSmsColumns.BODY)),
                    c.getInt(c.getColumnIndex(Telephony.TextBasedSmsColumns.THREAD_ID)),
                    c.getInt(c.getColumnIndex(Telephony.TextBasedSmsColumns.PERSON)),
                    c.getInt(c.getColumnIndex(Telephony.TextBasedSmsColumns.READ))
                )
                list.add(smsModel)
            } while (c.moveToNext())
        }
        c.close()
        return list
    }
}