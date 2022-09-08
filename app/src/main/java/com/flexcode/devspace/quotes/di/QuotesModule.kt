package com.flexcode.devspace.quotes.di

import android.content.Context
import androidx.room.Room
import com.flexcode.devspace.core.utils.Constants
import com.flexcode.devspace.github.data.local.GithubDatabase
import com.flexcode.devspace.quotes.data.local.QuotesDatabase
import com.flexcode.devspace.quotes.data.remote.QuotesApi
import com.flexcode.devspace.quotes.data.repository.GetAllQuotesRepositoryImpl
import com.flexcode.devspace.quotes.domain.repository.GetAllQuotesRepository
import com.flexcode.devspace.quotes.domain.usecases.GetAllQuotesUseCase
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
object QuotesModule {

    @Provides
    @Singleton
    fun providesQuotesApi(okHttpClient: OkHttpClient): QuotesApi{
        return Retrofit.Builder()
            .baseUrl(Constants.QUOTES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(QuotesApi::class.java)
    }

    @Singleton
    @Provides
    fun providesGithubDatabase(
        @ApplicationContext context: Context
    ): QuotesDatabase {
        return Room.databaseBuilder(
            context,
            QuotesDatabase::class.java,
            "quotes_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun providesGetQuotesRepository(
        database: QuotesDatabase,
        api: QuotesApi
    ) : GetAllQuotesRepository{
        return GetAllQuotesRepositoryImpl(
            dao = database.quotesDao,
            api = api
        )

    }

    @Provides
    @Singleton
    fun providesGetAllQuotesRepositoryUseCase(repository: GetAllQuotesRepository) : GetAllQuotesUseCase{
        return GetAllQuotesUseCase(
            repository
        )
    }

}