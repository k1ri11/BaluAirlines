package com.company.baluairlines.core.utils

import androidx.lifecycle.LiveData

interface NetworkUtils {
    fun hasInternetConnection(): Boolean

    fun getNetworkLiveData(): LiveData<Boolean>
}