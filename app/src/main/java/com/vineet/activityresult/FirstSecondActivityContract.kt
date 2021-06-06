package com.vineet.activityresult

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class FirstSecondActivityContract : ActivityResultContract<PaymentData, TransactionResultData>() {

    override fun createIntent(context: Context, input: PaymentData?): Intent {
        return Intent(context, SecondActivity::class.java).apply {
            input?.run {
                putExtra("recipientName", recipientName)
                putExtra("amount", amount)
            }
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): TransactionResultData {
        val transactionResultData = intent?.getParcelableExtra<TransactionResultData>("transactionStatus")

        return if (resultCode == Activity.RESULT_OK && transactionResultData != null)
            transactionResultData
        else
            TransactionResultData(false, "Technical Error")
    }

}