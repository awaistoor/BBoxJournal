package com.bbox.bboxjournal.domain.usecase

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.text.SimpleDateFormat
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class GetCurrentDateTimeUseCaseTest {

    private lateinit var SUT: GetCurrentDateTimeUseCase

    @Before
    fun setup(){
        SUT = GetCurrentDateTimeUseCase()
    }

    @Test
    fun `returns current date and time string in ISO format`() {
        val actualDateTime = SUT.invoke()
        Assert.assertNotNull(actualDateTime)
        val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        isoFormat.parse(actualDateTime)
    }
}