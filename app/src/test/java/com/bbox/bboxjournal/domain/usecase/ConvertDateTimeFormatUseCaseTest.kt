package com.bbox.bboxjournal.domain.usecase

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ConvertDateTimeFormatUseCaseTest {

    private lateinit var SUT: ConvertDateTimeFormatUseCase

    @Before
    fun setup(){
        SUT = ConvertDateTimeFormatUseCase()
    }

    @Test
    fun `convert date time string into different format`() {
        val result = SUT.invoke(getCorrectDateTime(), getCorrectFormat())
        Assert.assertEquals(getCorrectFormattedDate(), result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throw exception if wrong format is given`(){
        SUT.invoke(getCorrectDateTime(), "abcdefg")
    }

    private fun getCorrectFormattedDate(): String = "March 17, 2023"

    private fun getCorrectFormat(): String = "MMMM dd, yyyy"

    private fun getCorrectDateTime(): String = "2023-03-17T01:23:03.569+0500"

}