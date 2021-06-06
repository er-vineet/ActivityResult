package com.vineet.activityresult

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransactionResultData(val isTransactionSuccess: Boolean, val message: String) : Parcelable
