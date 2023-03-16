package com.bbox.bboxjournal.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbox.bboxjournal.domain.usecase.GetAllJournalsUseCase
import com.bbox.bboxjournal.presentation.home.adapter.ListUiModel
import com.bbox.bboxjournal.presentation.home.ui.HomeViewState
import com.bbox.bboxjournal.presentation.model.toJournalUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllJournalsUseCase: GetAllJournalsUseCase) :
    ViewModel() {
    private val homeViewState = MutableLiveData<HomeViewState>()
    val homeUiState: LiveData<HomeViewState> = homeViewState

    init {
        getAllJournals()
    }

    fun getAllJournals() {
        viewModelScope.launch {
            homeViewState.postValue(HomeViewState.Loading)
            try {
                val uiList = arrayListOf<ListUiModel>()
                val list = getAllJournalsUseCase.invoke()
                list.forEach { (key, value) ->
                    uiList.add(
                        ListUiModel(
                            itemType = ListUiModel.ItemType.MONTH_HEADER_ITEM,
                            headerText = key
                        )
                    )
                    value.forEach { (key, value) ->
                        uiList.add(
                            ListUiModel(
                                itemType = ListUiModel.ItemType.DAY_HEADER_ITEM,
                                headerText = key
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

                homeViewState.postValue(HomeViewState.Success(uiList))
            } catch (ex: Exception) {
                ex.printStackTrace()
                homeViewState.postValue(HomeViewState.Error)
            }
        }
    }
}