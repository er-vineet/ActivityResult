package com.vineet.activityresult

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        receiveDataFromFirstActivity()
    }

    private fun receiveDataFromFirstActivity() {
        intent?.run {
            val recipientName   = getStringExtra("recipientName")
            val amount          = getStringExtra("amount")
            val text = "Recipient: $recipientName\nAmount: ₹$amount"
            tvDetails.text = text
        }
    }

    fun onClickSuccessPayment(view: View?) {
        val transactionResultData = TransactionResultData(true, "Transaction Successful")
        setResult(RESULT_OK, Intent().putExtra("transactionStatus", transactionResultData))
        finish()
    }

    fun onClickDeclinePayment(view: View?) {
        val transactionResultData = TransactionResultData(false, "Transaction Failed")
        setResult(RESULT_OK, Intent().putExtra("transactionStatus", transactionResultData))
        finish()
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }

}