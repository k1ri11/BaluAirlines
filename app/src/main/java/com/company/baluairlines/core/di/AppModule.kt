package com.company.baluairlines.core.di

import android.content.Context
import androidx.room.Room
import com.company.baluairlines.core.data.AirApi
import com.company.baluairlines.core.data.database.AirDao
import com.company.baluairlines.core.data.database.AirDatabase
import com.company.baluairlines.core.utils.NetworkUtils
import com.company.baluairlines.core.utils.NetworkUtilsImpl
//import com.company.baluairlines.data.models.YourDeserializer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@ApplicationScope
@Module
class AppModule {

    @ApplicationScope
    @Provides
    fun getToDoDatabase(context: Context): AirDatabase {

        return Room.databaseBuilder(
            context.applicationContext,
            AirDatabase::class.java,
            "air_database"
        ).build()
    }

    @ApplicationScope
    @Provides
    fun provideDao(db: AirDatabase): AirDao {
        return db.airDao()
    }

    @ApplicationScope
    @Provides
    fun provideNetworkUtils(context: Context): NetworkUtils {
        return NetworkUtilsImpl(context)
    }

    @ApplicationScope
    @Provides
    fun provideClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @ApplicationScope
    @Provides
    fun provideAirApi(client: OkHttpClient): AirApi {
        return Retrofit.Builder()
            .baseUrl("http://95.165.11.56:7654")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(AirApi::class.java)
    }

}