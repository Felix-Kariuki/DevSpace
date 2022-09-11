package com.flexcode.devspace.github.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.flexcode.devspace.github.data.local.dao.UserDao
import com.flexcode.devspace.github.data.local.entities.UserEntity
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
        dao.insertUser(fakeUserEntity)

        val userDetails = dao.getUser("Felix-Kariuki")

        Truth.assertThat(userDetails).isEqualTo(fakeUserEntity)
    }

    @Test
    fun `DELETE USER DETAILS FROM DATABASE`() = runTest {
        dao.insertUser(fakeUserEntity)
        dao.deleteUser()

        val userDetails = dao.getUser("Felix-Kariuki")

        Truth.assertThat(userDetails).isNull()
    }
}

val fakeUserEntity = UserEntity(
    avatar_url = "https://avatars.githubusercontent.com/u/61313608?v=4",
    bio = "Native Android developer",
    blog = "https://felixkariuki.netlify.app/",
    email = null,
    followers = 42,
    following = 36,
    id = 61313608,
    location = "Nairobi,Kenya",
    login = "Felix-Kariuki",
    name = "Felix Kariuki",
    public_repos = 48,
    twitter_username = "felixkariuki_",
    url = "https://api.github.com/users/Felix-Kariuki"
)