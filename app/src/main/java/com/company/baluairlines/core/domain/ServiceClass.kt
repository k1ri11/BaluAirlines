package com.company.baluairlines.core.domain

/**
 * класс содержащий классы обслуживания пассажиров
 * [Economy] эконом класс
 * [Business] бизнес класс
 */
sealed class ServiceClass{
    /** эконом класс  */
    object Economy: ServiceClass()
//    object Comfort: ServiceClass()
    /** бизнес класс  */
    object Business: ServiceClass()
}

