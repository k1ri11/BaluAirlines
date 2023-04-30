package com.company.baluairlines.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.company.baluairlines.core.di.ApplicationScope
import javax.inject.Inject

/**
 * Содержит методы для отслеживания состояния интернет соединения
 * @property networkLiveData содержит статус текущего подключения
 * @param context [Context]
 */
@ApplicationScope
class NetworkUtilsImpl @Inject constructor(val context: Context) :
    ConnectivityManager.NetworkCallback(), NetworkUtils {

    private val networkLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    /**
     * метод проверки текущего состояния интернет соединения
     * @return присутствует ли соединение
     */
    override fun hasInternetConnection(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    /**
     * метод возвращающий [LiveData] с статусом интернет соединения
     * @return присутствует ли соединение обернутое в [LiveData]
     */
    override fun getNetworkLiveData(): LiveData<Boolean> {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(this)
        } else {
            val builder = NetworkRequest.Builder()
            connectivityManager.registerNetworkCallback(builder.build(), this)
        }

        networkLiveData.postValue(hasInternetConnection())
        return networkLiveData
    }

    /**
     * коллбэк вызывающийся при появлении соединения с интернетом и обновляет актуальное состояние подключения
     */
    override fun onAvailable(network: Network) {
        networkLiveData.postValue(true)
    }

    /**
     * коллбэк вызывающийся при отсутствии соединения с интернетом и обновляет актуальное состояние подключения
     */
    override fun onLost(network: Network) {
        networkLiveData.postValue(false)
    }
}