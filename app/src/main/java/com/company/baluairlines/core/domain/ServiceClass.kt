package com.company.baluairlines.core.domain

import android.os.Parcelable
import com.company.baluairlines.core.domain.ServiceClass.Business
import com.company.baluairlines.core.domain.ServiceClass.Economy
import kotlinx.parcelize.Parcelize


/**
 * класс содержащий классы обслуживания пассажиров
 * [Economy] эконом класс
 * [Business] бизнес класс
 */
sealed class ServiceClass: Parcelable {
    /** эконом класс  */
    @Parcelize
    object Economy: ServiceClass()
//    object Comfort: ServiceClass()
    /** бизнес класс  */
    @Parcelize
    object Business: ServiceClass()

}

