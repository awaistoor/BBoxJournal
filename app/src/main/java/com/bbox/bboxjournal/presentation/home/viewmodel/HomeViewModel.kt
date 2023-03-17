package com.bbox.bboxjournal.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbox.bboxjournal.domain.usecase.GetAllJournalsUseCase
import com.bbox.bboxjournal.domain.usecase.GetCumulativeMoodColorUseCase
import com.bbox.bboxjournal.domain.usecase.GetEntriesCountFromMapUseCase
import com.bbox.bboxjournal.presentation.home.adapter.ListUiModel
import com.bbox.bboxjournal.presentation.home.ui.HomeViewState
import com.bbox.bboxjournal.presentation.model.MoodColorUiModel
import com.bbox.bboxjournal.presentation.model.toJournalUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllJournalsUseCase: GetAllJournalsUseCase,
    private val getEntriesCountFromMapUseCase: GetEntriesCountFromMapUseCase,
    private val getCumulativeMoodColorUseCase: GetCumulativeMoodColorUseCase
) :
    ViewModel() {
    // a mutable live data to observe the UI state
    private val homeViewState = MutableLiveData<HomeViewState>()
    // a live data which will expose the view state to the view
    val homeUiState: LiveData<HomeViewState> = homeViewState

    fun getAllJournals() {
        viewModelScope.launch {
            // emit UI state to Loading
            homeViewState.postValue(HomeViewState.Loading)
            try {
                // create a array list of ListUiModel which will be given to adapter
                val uiList = arrayListOf<ListUiModel>()
                // request for all journals from the data source
                val list = getAllJournalsUseCase.invoke()
                list.forEach { (key, value) ->
                    uiList.add(
                        ListUiModel(
                            itemType = ListUiModel.ItemType.MONTH_HEADER_ITEM,
                            header = ListUiModel.HeaderUiModel(
                                headerTitle = key,
                                // get cumulative mood color and convert into MoodColorUiModel
                                headerColor = when (getCumulativeMoodColorUseCase.invoke(value)) {
                                    MoodColorUiModel.GREEN.name -> MoodColorUiModel.GREEN
                                    MoodColorUiModel.RED.name -> MoodColorUiModel.RED
                                    MoodColorUiModel.YELLOW.name -> MoodColorUiModel.YELLOW
                                    else -> MoodColorUiModel.GREEN
                                },
                                // get entry count here
                                entriesCount = getEntriesCountFromMapUseCase.invoke(value)
                            )
                        )
                    )
                    value.forEach { (key, value) ->
                        uiList.add(
                            ListUiModel(
                                itemType = ListUiModel.ItemType.DAY_HEADER_ITEM,
                                header = ListUiModel.HeaderUiModel(
                                    headerTitle = key,
                                    headerColor = MoodColorUiModel.GREEN,
                                    entriesCount = value.size
                                )
                            )
                        )
                        value.forEach { journal ->
                            uiList.add(
                                ListUiModel(
                                    itemType = ListUiModel.ItemType.LIST_ITEM,
                                    listItem = journal.toJournalUiModel()
                                )
                            )
                        }
                    }
                }
                // emit UI state to Success with uiList
                homeViewState.postValue(HomeViewState.Success(uiList))
            } catch (ex: Exception) {
                ex.printStackTrace()
                // emit UI state to Error
                homeViewState.postValue(HomeViewState.Error)
            }
        }
    }
}