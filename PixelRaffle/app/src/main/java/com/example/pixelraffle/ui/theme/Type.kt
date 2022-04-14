package com.example.pixelraffle.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pixelraffle.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)


val PressStart =FontFamily(

    Font(R.font.pressstart_regular,weight = FontWeight.Bold)

)

val CustomTypography = Typography(
    body1 = TextStyle(
        fontFamily = PressStart,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

//    button = TextStyle(
//        fontFamily = PressStart,
//        fontWeight = FontWeight.W500,
//        fontSize = 10.sp
//    ),
//    caption = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp
//    )


)