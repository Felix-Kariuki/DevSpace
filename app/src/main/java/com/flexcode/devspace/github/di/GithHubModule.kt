package com.flexcode.devspace.github.di

import android.content.Context
import androidx.room.Room
import com.flexcode.devspace.core.utils.Constants
import com.flexcode.devspace.github.data.local.GithubDatabase
import com.flexcode.devspace.github.data.remote.GitApi
import com.flexcode.devspace.github.data.repository.GetFollowersRepositoryImpl
import com.flexcode.devspace.github.data.repository.GetFollowingRepositoryImpl
import com.flexcode.devspace.github.data.repository.GetRepositoryImpl
import com.flexcode.devspace.github.data.repository.GetUserRepositoryImpl
import com.flexcode.devspace.github.domain.repository.GetFollowersRepository
import com.flexcode.devspace.github.domain.repository.GetFollowingRepository
import com.flexcode.devspace.github.domain.repository.GetReposRepository
import com.flexcode.devspace.github.domain.repository.GetUserRepository
import com.flexcode.devspace.github.domain.use_cases.GetFollowersUseCase
import com.flexcode.devspace.github.domain.use_cases.GetFollowingUseCase
import com.flexcode.devspace.github.domain.use_cases.GetRepositoryUseCase
import com.flexcode.devspace.github.domain.use_cases.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object GithHubModule {

    @Singleton
    @Provides
    fun providesGithubDatabase(
        @ApplicationContext context: Context
    ): GithubDatabase {
        return Room.databaseBuilder(
            context,
            GithubDatabase::class.java,
            "github_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesGithubApi(okHttpClient: OkHttpClient): GitApi {
        return Retrofit.Builder()
            .baseUrl(Constants.GITHUB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GitApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGetUserDetailsRepository(
        database: GithubDatabase,
        api: GitApi
    ): GetUserRepository {
        return GetUserRepositoryImpl(
            api = api,
            dao = database.userDao
        )
    }

    @Provides
    @Singleton
    fun providesGetUserDetailsUseCase(repository: GetUserRepository): GetUserUseCase {
        return GetUserUseCase(
            repository
        )
    }

    @Provides
    @Singleton
    fun providesGetFollowersRepository(
        database: GithubDatabase,
        api: GitApi
    ): GetFollowersRepository {
        return GetFollowersRepositoryImpl(
            api = api,
            dao = database.followersDao
        )
    }

    @Provides
    @Singleton
    fun providesGetFollowersUseCase(repository: GetFollowersRepository): GetFollowersUseCase {
        return GetFollowersUseCase(
            repository
        )
    }

    @Provides
    @Singleton
    fun providesGetFollowingRepository(
        database: GithubDatabase,
        api: GitApi
    ): GetFollowingRepository {
        return GetFollowingRepositoryImpl(
            api = api,
            dao = database.followingDao
        )
    }

    @Provides
    @Singleton
    fun providesGetFollowingUseCase(repository: GetFollowingRepository): GetFollowingUseCase {
        return GetFollowingUseCase(
            repository
        )
    }

    @Provides
    @Singleton
    fun providesReposRepository(
        database: GithubDatabase,
        api: GitApi
    ): GetReposRepository {
        return GetRepositoryImpl(
            api = api,
            dao = database.repositoryDao
        )
    }

    @Provides
    @Singleton
    fun providesGetRepositoriesUseCase(repository: GetReposRepository): GetRepositoryUseCase {
        return GetRepositoryUseCase(
            repository
        )
    }
}
