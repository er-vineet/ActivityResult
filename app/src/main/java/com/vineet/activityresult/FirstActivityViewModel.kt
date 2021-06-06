package com.vineet.activityresult

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class FirstActivityViewModel : ViewModel() {

    var recipientName           = ""
    var amount                  = ""

    var errorMsgRecipientName   = MutableLiveData<String?>()
    var errorMsgAmount          = MutableLiveData<String?>()

    val liveDataPaymentData     = MutableLiveData<PaymentData>()

    fun onClickSendMoney() {
        if (validate()) {
            val paymentData = PaymentData(recipientName, amount)
            liveDataPaymentData.value = paymentData
        }
    }

    private fun validate() : Boolean {
        errorMsgRecipientName.value     = null
        errorMsgAmount.value            = null

        if (recipientName.isBlank()) {
            errorMsgRecipientName.value = "Enter Recipient Name"
            return false
        }
        if (amount.isBlank()) {
            errorMsgAmount.value = "Enter Amount"
            return false
        }
        return true
    }

}