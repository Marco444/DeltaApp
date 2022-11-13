package com.example.myapplication.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

val H1Font = FontFamily(
    Font(R.font.bebas_neue, FontWeight.Normal)
)

val NormalFont = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
     defaultFontFamily = FontFamily(Font(R.font.bebas_neue)),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontSize = 70.sp,
        color = Green,
    ),
    h3 = TextStyle(
        fontSize = 50.sp,
        fontFamily = FontFamily(Font(R.font.roboto_regular, FontWeight.Normal)),
        color = Green,
    ),
    /* Other default text styles to override
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
    */
)