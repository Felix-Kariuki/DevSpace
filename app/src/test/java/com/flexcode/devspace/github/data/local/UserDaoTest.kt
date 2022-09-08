package com.flexcode.devspace.github.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.flexcode.devspace.github.data.entities.FakeUserEntity
import com.flexcode.devspace.github.data.local.dao.UserDao
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
class UserDaoTest {

    private lateinit var db:GithubDatabase
    private lateinit var dao: UserDao

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context,GithubDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.userDao

    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }


    @Test
    fun `store user details in Database`() = runTest {
        dao.insertUser(FakeUserEntity().fakeUserEntity)

        val userDetails = dao.getUser("Felix-Kariuki")

        Truth.assertThat(userDetails).isEqualTo(FakeUserEntity().fakeUserEntity)
    }

    @Test
    fun `DELETE USER DETAILS FROM DATABASE`() = runTest {
        dao.insertUser(FakeUserEntity().fakeUserEntity)
        dao.deleteUser()

        val userDetails = dao.getUser("Felix-Kariuki")

        Truth.assertThat(userDetails).isNull()
    }
}