package com.bbox.bboxjournal.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbox.bboxjournal.domain.usecase.GetAllJournalsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllJournalsUseCase: GetAllJournalsUseCase) :
    ViewModel() {

    fun testingDB() =
        viewModelScope.launch {
            val values = getAllJournalsUseCase.invoke()
            println(values)
        }


}