package com.bbox.bboxjournal.presentation.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bbox.bboxjournal.domain.model.JournalDomainModel
import com.bbox.bboxjournal.domain.usecase.GetAllJournalsUseCase
import com.bbox.bboxjournal.domain.usecase.GetCumulativeMoodColorUseCase
import com.bbox.bboxjournal.domain.usecase.GetEntriesCountFromMapUseCase
import com.bbox.bboxjournal.presentation.home.adapter.ListUiModel
import com.bbox.bboxjournal.presentation.home.ui.HomeViewState
import com.bbox.bboxjournal.presentation.model.JournalUiModel
import com.bbox.bboxjournal.presentation.model.MoodColorUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var SUT: HomeViewModel

    @Mock
    private lateinit var getAllJournalsUseCase: GetAllJournalsUseCase

    @Mock
    private lateinit var getEntriesCountFromMapUseCase: GetEntriesCountFromMapUseCase

    @Mock
    private lateinit var getCumulativeMoodColorUseCase: GetCumulativeMoodColorUseCase

    @Mock
    private lateinit var homeViewStateObserver: Observer<HomeViewState>

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        SUT = HomeViewModel(
            getAllJournalsUseCase = getAllJournalsUseCase,
            getEntriesCountFromMapUseCase = getEntriesCountFromMapUseCase,
            getCumulativeMoodColorUseCase = getCumulativeMoodColorUseCase
        )
    }

    @Test
    fun `when getAllJournals, invoke getAllJournalsUseCase, invoke getCumulativeMoodColorUseCase, invoke getEntriesCountFromMapUseCase, return success with data`() =
        runBlocking {
            Mockito.`when`(getAllJournalsUseCase.invoke()).thenReturn(getDummyHashMap())
            Mockito.`when`(getCumulativeMoodColorUseCase.invoke(getDummyMap())).thenReturn("GREEN")
            Mockito.`when`(getEntriesCountFromMapUseCase.invoke(getDummyMap())).thenReturn(0)
            SUT.getAllJournals()
            SUT.homeUiState.observeForever(homeViewStateObserver)
            Mockito.verify(getAllJournalsUseCase).invoke()
            Mockito.verify(getCumulativeMoodColorUseCase).invoke(getDummyMap())
            Mockito.verify(getEntriesCountFromMapUseCase).invoke(getDummyMap())
            Mockito.verify(homeViewStateObserver)
                .onChanged(HomeViewState.Success(getDummyUiModel()))
            SUT.homeUiState.removeObserver(homeViewStateObserver)
        }

    @Test
    fun `when getAllJournals, invoke getAllJournalsUseCase, throw exception, return error`() =
        runBlocking {
            Mockito.`when`(getAllJournalsUseCase.invoke()).thenThrow(RuntimeException())
            Mockito.`when`(getCumulativeMoodColorUseCase.invoke(getDummyMap())).thenReturn("GREEN")
            Mockito.`when`(getEntriesCountFromMapUseCase.invoke(getDummyMap())).thenReturn(0)
            SUT.getAllJournals()
            SUT.homeUiState.observeForever(homeViewStateObserver)
            Mockito.verify(getAllJournalsUseCase).invoke()
            Mockito.verify(getCumulativeMoodColorUseCase, times(0)).invoke(getDummyMap())
            Mockito.verify(getEntriesCountFromMapUseCase, times(0)).invoke(getDummyMap())
            Mockito.verify(homeViewStateObserver)
                .onChanged(HomeViewState.Error)
            SUT.homeUiState.removeObserver(homeViewStateObserver)
        }

    private fun getDummyMap(): Map<String, List<JournalDomainModel>> {
        return getDummyHashMap()["March, 2023"]!!
    }

    private fun getDummyUiModel(): List<ListUiModel> {
        return listOf(
            ListUiModel(
                itemType = ListUiModel.ItemType.MONTH_HEADER_ITEM,
                header = ListUiModel.HeaderUiModel(
                    headerTitle = "March, 2023",
                    headerColor = MoodColorUiModel.GREEN,
                    entriesCount = 0
                ),
                listItem = null
            ),
            ListUiModel(
                itemType = ListUiModel.ItemType.DAY_HEADER_ITEM, header = ListUiModel.HeaderUiModel(
                    headerTitle = "Tue, 15",
                    headerColor = MoodColorUiModel.GREEN,
                    entriesCount = 2
                ), listItem = null
            ),
            ListUiModel(
                itemType = ListUiModel.ItemType.LIST_ITEM,
                header = null,
                listItem = JournalUiModel(
                    id = 0,
                    moodColor = MoodColorUiModel.GREEN,
                    note = "",
                    dateTime = ""
                )
            ),
            ListUiModel(
                itemType = ListUiModel.ItemType.LIST_ITEM,
                header = null,
                listItem = JournalUiModel(
                    id = 0,
                    moodColor = MoodColorUiModel.GREEN,
                    note = "",
                    dateTime = ""
                )
            ),
            ListUiModel(
                itemType = ListUiModel.ItemType.DAY_HEADER_ITEM, header = ListUiModel.HeaderUiModel(
                    headerTitle = "Wed, 16",
                    headerColor = MoodColorUiModel.GREEN,
                    entriesCount = 2
                ), listItem = null
            ),
            ListUiModel(
                itemType = ListUiModel.ItemType.LIST_ITEM,
                header = null,
                listItem = JournalUiModel(
                    id = 0,
                    moodColor = MoodColorUiModel.GREEN,
                    note = "",
                    dateTime = ""
                )
            ),
            ListUiModel(
                itemType = ListUiModel.ItemType.LIST_ITEM,
                header = null,
                listItem = JournalUiModel(
                    id = 0,
                    moodColor = MoodColorUiModel.GREEN,
                    note = "",
                    dateTime = ""
                )
            )
        )
    }

    private fun getDummyHashMap(): HashMap<String, Map<String, List<JournalDomainModel>>> {
        return hashMapOf(
            "March, 2023" to mapOf(
                "Tue, 15" to listOf(
                    JournalDomainModel(
                        id = 0,
                        note = "",
                        dateTime = "",
                        moodColor = ""
                    ),
                    JournalDomainModel(
                        id = 0,
                        note = "",
                        dateTime = "",
                        moodColor = ""
                    )
                ),
                "Wed, 16" to listOf(
                    JournalDomainModel(
                        id = 0,
                        note = "",
                        dateTime = "",
                        moodColor = ""
                    ),
                    JournalDomainModel(
                        id = 0,
                        note = "",
                        dateTime = "",
                        moodColor = ""
                    )
                )

            )
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}