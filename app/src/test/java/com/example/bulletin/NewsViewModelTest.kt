package com.example.bulletin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bulletin.model.NewsResponse
import com.example.bulletin.repository.NewsRepository
import com.example.bulletin.utils.UiState
import com.example.bulletin.viewmodels.NewsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: NewsRepository
    private lateinit var viewModel: NewsViewModel

    private val fakeResponse = NewsResponse(
        totalArticles = 0,
        articles = emptyList()
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        repository = mockk(relaxed = true)
        viewModel = NewsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun test_success_state() = runTest {

        coEvery {
            repository.getBreakingNews(any(), any())
        } returns UiState.Success(fakeResponse)

        viewModel.getNews("general", "key")

        testDispatcher.scheduler.advanceUntilIdle()

        val result = viewModel.newsData.value

        assertTrue(result is UiState.Success)
    }
}