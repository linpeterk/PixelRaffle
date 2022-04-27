package com.revature.pixelraffle

import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class EmailValidatorTest{
    @Test
    fun emailValidator_CorrectEmail_ReturnTure(){
        //assertTrue(EmailValidator.isValideEmail("name@gmail.com"))
    }
}
