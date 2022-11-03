package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Green


@Composable
fun RoutineCardDetails(title: String) {
    Text(
        text = "Lorum ipsum dorum Lorum ipsum doru Lorum ipsum doru Lorum ipsum doru Lorum ipsum",
        fontSize = 25.sp,
        color = Green,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(0.8f),
        textAlign = TextAlign.Justify
    )
}

@Composable
fun RoutineCardTitle(title: String, iconId: Int, clickedIcon: () -> Unit = {}) {
    Row (verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            fontSize = 50.sp,
            color = Color.White,
            modifier = Modifier,
            textAlign = TextAlign.Start,
        )
        Image(
            painter = painterResource(iconId),
            contentDescription = null,
            modifier = Modifier
                .scale(1.5F)
                .padding(start = 70.dp) //esto sirve como un padding pero mas responsivo
                .clickable(onClick = clickedIcon)
        )
    }
}

@Composable
fun RoutineCard(backgroundImageId: Int, iconIdUnclicked: Int, iconIdClicked: Int, title: String, clickedIcon: () -> Unit = {}) {

    var expanded by rememberSaveable { mutableStateOf(false) }
    var iconClicked by rememberSaveable { mutableStateOf(false) }
    var imageHeight by remember { mutableStateOf(70.dp) }

    Box (
        Modifier
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
                imageHeight = if (expanded) 200.dp else 70.dp
            },
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(backgroundImageId),
            contentDescription = null,
            modifier = Modifier
                .width(370.dp)
                .clip(RoundedCornerShape(20.dp))
                .height(imageHeight),
            contentScale = ContentScale.Crop,
        )
        Column (
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 40.dp)
        ) {

            RoutineCardTitle(
                title = title,
                iconId = if(iconClicked) iconIdClicked else iconIdUnclicked,
                clickedIcon = {clickedIcon(); iconClicked = !iconClicked}
            )
            if (expanded)
                RoutineCardDetails(title = "")
        }

    }

    Spacer(modifier = Modifier.height(30.dp))


}
