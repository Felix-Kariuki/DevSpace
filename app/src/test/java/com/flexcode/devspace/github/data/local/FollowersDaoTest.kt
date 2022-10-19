package com.flexcode.devspace.github.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.flexcode.devspace.github.data.local.dao.FollowersDao
import com.flexcode.devspace.github.data.local.entities.FollowersEntity
import com.google.common.truth.Truth
import java.io.IOException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@SmallTest
@OptIn(ExperimentalCoroutinesApi::class)
class FollowersDaoTest {

    private lateinit var db: GithubDatabase
    private lateinit var dao: FollowersDao

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, GithubDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.followersDao
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }

    @Test
    fun `store Followers in Database`() = runTest {
        dao.insertFollowers(fakeFollowersEntity)
        val followers = dao.getUserFollowers()
        Truth.assertThat(followers).isEqualTo(fakeFollowersEntity)
    }

    @Test
    fun `DELETE FOLLOWERS FROM DATABASE`() = runTest {
        dao.insertFollowers(fakeFollowersEntity)
        dao.deleteFollowers()
        val followers = dao.getUserFollowers()
        /**
         * Assert that the followers is empty not null as it's a list
         */
        Truth.assertThat(followers).isEmpty()
    }
}

val fakeFollowersEntity =
    listOf(
        FollowersEntity(
            avatar_url = "https://avatars.githubusercontent.com/u/17609923?v=4",
            id = 17609923,
            login = "mancini85"
        )
    )
