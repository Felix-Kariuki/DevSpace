package com.flexcode.devspace.core.di

import android.content.Context
import android.content.SharedPreferences
import com.flexcode.devspace.core.utils.Constants.KEY_DARK_THEME
import com.flexcode.devspace.core.utils.Constants.KEY_GITHUB_USERNAME
import com.flexcode.devspace.core.utils.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext app: Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)


    @Singleton
    @Provides
    fun providesGithubUserName(sharedPreferences: SharedPreferences) =
        sharedPreferences.getString(KEY_GITHUB_USERNAME, "empty")



    @Singleton
    @Provides
    fun providesDarkTheme(sharedPreferences: SharedPreferences) =
        sharedPreferences.getBoolean(KEY_DARK_THEME, false)

    @Singleton
    @Provides
    fun providesLightTheme(sharedPreferences: SharedPreferences) =
        sharedPreferences.getBoolean(KEY_DARK_THEME, false)
}