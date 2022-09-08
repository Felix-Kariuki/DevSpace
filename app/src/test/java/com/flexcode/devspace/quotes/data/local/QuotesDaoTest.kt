package com.flexcode.devspace.quotes.data.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.flexcode.devspace.quotes.data.entity.fakeQuotesEntity
import com.flexcode.devspace.quotes.data.local.dao.QuotesDao
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
class QuotesDaoTest {

    private lateinit var db : QuotesDatabase
    private lateinit var dao: QuotesDao

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, QuotesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.quotesDao
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.clearAllTables()
        db.close()
    }


    @Test
    fun `STORE QUOTES IN DATABASE`() = runTest{
        dao.insertQuotes(fakeQuotesEntity)
        val quotes = dao.getAllQuotes()
        Truth.assertThat(quotes).isEqualTo(fakeQuotesEntity)
    }

    @Test
    fun `DELETE QUOTES FROM DATABASE`() = runTest{
        dao.insertQuotes(fakeQuotesEntity)
        dao.deleteQuotes()
        val quotes = dao.getAllQuotes()
        Truth.assertThat(quotes).isEmpty()
    }

}