package com.flexcode.devspace.github.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.flexcode.devspace.github.data.entities.fakeUserRepoEntity
import com.flexcode.devspace.github.data.local.dao.RepositoryDao
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@SmallTest
@OptIn(ExperimentalCoroutinesApi::class)
class ReposDaoTest {
    private lateinit var db: GithubDatabase
    private lateinit var dao: RepositoryDao

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, GithubDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.repositoryDao
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `store FOLLOWING in Database`() = runTest {
        dao.insertRepositories(fakeUserRepoEntity)
        val followers = dao.getRepositories()
        Truth.assertThat(followers).isEqualTo(fakeUserRepoEntity)
    }

    @Test
    fun `DELETE FOLLOWING FROM DATABASE`() = runTest {
        dao.insertRepositories(fakeUserRepoEntity)
        dao.deleteRepositories()
        val followers = dao.getRepositories()
        /**
         * Assert that the Repos is empty not null as it's a list
         */
        Truth.assertThat(followers).isEmpty()
    }
}