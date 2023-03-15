package com.bbox.bboxjournal.presentation.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbox.bboxjournal.domain.usecase.ConvertDateTimeFormatUseCase
import com.bbox.bboxjournal.domain.usecase.GetAllJournalsUseCase
import com.bbox.bboxjournal.presentation.home.adapter.ListUiModel
import com.bbox.bboxjournal.presentation.home.ui.HomeViewState
import com.bbox.bboxjournal.presentation.model.toJournalUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllJournalsUseCase: GetAllJournalsUseCase,
    private val convertDateTimeFormatUseCase: ConvertDateTimeFormatUseCase
) :
    ViewModel() {
    val homeUiState = MutableLiveData<HomeViewState>()

    init {
        getAllJournals()
    }

    fun getAllJournals() {
        viewModelScope.launch {
            homeUiState.postValue(HomeViewState.Loading)
            try {
                val uiList = arrayListOf<ListUiModel>()
                val list = getAllJournalsUseCase.invoke()
                list.groupBy { convertDateTimeFormatUseCase(it.dateTime, "MMMM, yyyy") }
                    .forEach { (key, value) ->
                        uiList.add(
                            ListUiModel(
                                itemType = ListUiModel.ItemType.MONTH_HEADER_ITEM,
                                headerText = key
                            )
                        )
                        value.groupBy { convertDateTimeFormatUseCase(it.dateTime, "EEE, dd") }
                            .forEach { (k, v) ->
                                uiList.add(
                                    ListUiModel(
                                        itemType = ListUiModel.ItemType.DAY_HEADER_ITEM,
                                        headerText = k
                                    )
                                )
                                v.forEach {
                                    uiList.add(
                                        ListUiModel(
                                            itemType = ListUiModel.ItemType.LIST_ITEM,
                                            listItem = it.toJournalUiModel()
                                        )
                                    )
                                }
                            }
                    }

                homeUiState.postValue(HomeViewState.Success(uiList))
            } catch (ex: Exception) {
                ex.printStackTrace()
                homeUiState.postValue(HomeViewState.Error)
            }
        }
    }
}