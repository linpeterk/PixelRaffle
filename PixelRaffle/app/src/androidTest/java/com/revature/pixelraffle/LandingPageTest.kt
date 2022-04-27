package com.revature.pixelraffle

import android.icu.util.TimeUnit.values
import androidx.compose.ui.hapticfeedback.HapticFeedbackType.Companion.values
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.font.FontStyle.Companion.values
import androidx.compose.ui.text.style.TextAlign.Companion.values
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.pixelraffle.ui.screens.lyle.MainOverallScreen
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog


class LandingPageTest {

    @get:Rule
    val composeRule = createComposeRule()


    val mockNavController = mock(NavController::class.java)


    @Test
    fun isPixelLogoFound(){
//        val allScreens= MainActivity.values().toList()

        composeRule.setContent {
            MainOverallScreen(mockNavController)
        }
        composeRule.onRoot(useUnmergedTree = true).printToLog("Labels")
    //    composeRule.onNode(hasText())
    }
}