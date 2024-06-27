package com.testproject1.searchtickets.di

import android.content.Context
import androidx.room.Room
import com.testproject1.searchtickets.data.AppDatabase
import com.testproject1.searchtickets.data.TicketsApi
import com.testproject1.searchtickets.data.TicketsApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTicketsApi(): TicketsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(TicketsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "appdb"
        ).build().getDao()
}