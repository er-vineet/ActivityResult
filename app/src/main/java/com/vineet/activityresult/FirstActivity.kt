package com.vineet.activityresult

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vineet.activityresult.databinding.ActivityFirstBinding
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {

    private lateinit var viewModel: FirstActivityViewModel
    private var paymentData: PaymentData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindingAndViewModel()
        setObservables()
    }

    private fun setBindingAndViewModel() {
        val binding: ActivityFirstBinding = DataBindingUtil.setContentView(this, R.layout.activity_first)

        viewModel = ViewModelProvider(this).get(FirstActivityViewModel::class.java)
        binding.lifecycleOwner = this
        binding.firstActivityViewModel = viewModel
    }

    private fun setObservables() {
        viewModel.liveDataPaymentData.observe(this, observerSendMoneyClick)
    }

    private val observerSendMoneyClick = Observer<PaymentData> {
        paymentData = it
        activityResultContract.launch(paymentData)
    }

    private val activityResultContract = registerForActivityResult(FirstSecondActivityContract()) { transactionResult ->
        tvStatus.text = transactionResult.message

        if (transactionResult.isTransactionSuccess)
            clContainer.setBackgroundColor(Color.parseColor("#D0F0C0"))
        else
            clContainer.setBackgroundColor(Color.parseColor("#FFCCCB"))
    }

}