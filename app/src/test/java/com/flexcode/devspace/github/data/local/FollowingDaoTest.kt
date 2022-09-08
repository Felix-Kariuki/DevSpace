package com.flexcode.devspace.github.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.flexcode.devspace.github.data.entities.fakeFollowingEntity
import com.flexcode.devspace.github.data.local.dao.FollowingDao
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
class FollowingDaoTest {
    private lateinit var db: GithubDatabase
    private lateinit var dao: FollowingDao

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, GithubDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.followingDao
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `store FOLLOWING in Database`() = runTest {
        dao.insertFollowing(fakeFollowingEntity)
        val followers = dao.getFollowing()
        Truth.assertThat(followers).isEqualTo(fakeFollowingEntity)
    }

    @Test
    fun `DELETE FOLLOWING FROM DATABASE`() = runTest {
        dao.insertFollowing(fakeFollowingEntity)
        dao.deleteFollowing()
        val followers = dao.getFollowing()
        /**
         * Assert that the followers is empty not null as it's a list
         */
        Truth.assertThat(followers).isEmpty()
    }
}