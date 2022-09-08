package com.flexcode.devspace.wakatime.di

import android.content.Context
import androidx.room.Room
import com.flexcode.devspace.core.utils.Constants
import com.flexcode.devspace.wakatime.data.local.WakatimeDatabase
import com.flexcode.devspace.wakatime.data.remote.WakatimeApi
import com.flexcode.devspace.wakatime.data.repository.GetEditorsRepositoryImpl
import com.flexcode.devspace.wakatime.domain.repository.GetEditorsRepository
import com.flexcode.devspace.wakatime.domain.usecases.GetEditorsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WakatimeModule {


    @Provides
    @Singleton
    fun providesWakatimeApi(okHttpClient: OkHttpClient): WakatimeApi{
        return Retrofit.Builder()
            .baseUrl(Constants.WAKATIME_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(WakatimeApi::class.java)
    }

    @Singleton
    @Provides fun providesWakatimeDatabase(
        @ApplicationContext context: Context
    ): WakatimeDatabase{
        return Room.databaseBuilder(
            context,
            WakatimeDatabase::class.java,
            "wakatime_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesGetEditorsRepository(
        database: WakatimeDatabase,
        api: WakatimeApi
    ): GetEditorsRepository{
        return GetEditorsRepositoryImpl(
           api=api,
           dao = database.editorsDao
        )
    }

    @Provides
    @Singleton
    fun providesGetEditorsUseCase(repository: GetEditorsRepository) : GetEditorsUseCase{
        return GetEditorsUseCase(
            repository
        )
    }
}