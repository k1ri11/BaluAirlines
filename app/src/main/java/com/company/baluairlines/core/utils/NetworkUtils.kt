package com.company.baluairlines.core.utils

import androidx.lifecycle.LiveData

/**
 * Содержит методы для отслеживания состояния интернет соединения
 */
interface NetworkUtils {
    fun hasInternetConnection(): Boolean

    fun getNetworkLiveData(): LiveData<Boolean>
}