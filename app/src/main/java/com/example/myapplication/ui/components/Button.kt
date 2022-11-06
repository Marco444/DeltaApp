package com.example.myapplication.ui.components

import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.BackgroundButton
import com.example.myapplication.ui.theme.Black
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.theme.H1Font


@Composable
fun Button1(fontSize: Int, text: String, handler: () -> Unit = {}) {
    Button(
        onClick = handler,
        colors = ButtonDefaults.buttonColors(backgroundColor = BackgroundButton),
        border = BorderStroke(3.dp, Green),
        shape = RoundedCornerShape(20)
    ) {
        Text(text = text,
            fontSize = fontSize.sp,
            fontFamily = H1Font,
            color = Green,
            modifier = Modifier.padding(7.dp)
        )
    }
}

