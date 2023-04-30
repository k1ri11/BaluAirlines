package com.company.baluairlines.core.di

import com.company.baluairlines.buy_ticket_feature.data.BuyTicketRepository
import com.company.baluairlines.buy_ticket_feature.domain.BuyTicketRepositoryImpl
import com.company.baluairlines.services_feature.data.ServicesRepository
import com.company.baluairlines.services_feature.domain.ServicesRepositoryImpl
import dagger.Binds
import dagger.Module

//todo: убрать в ServicesFlightComponent (после правильного инжекта viewmodel)

/** интерфейс связывающий для даггера интрефейс репозитория и его реализацию */
@Module
interface AppModuleBinds {

    /** функция связки [ServicesRepository] и [ServicesRepositoryImpl] */
    @ApplicationScope
    @Binds
    fun getServicesRepository(repository: ServicesRepositoryImpl): ServicesRepository

    /** функция связки [BuyTicketRepository] и [BuyTicketRepositoryImpl] */
    @ApplicationScope
    @Binds
    fun getBuyTicketRepository(repository: BuyTicketRepositoryImpl): BuyTicketRepository

}