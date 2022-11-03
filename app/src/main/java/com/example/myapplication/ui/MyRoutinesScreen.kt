package com.example.myapplication.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.R

@Composable
fun SortButtons() {
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Gray)
            .padding(5.dp),

        ){
        SortButton(onClick ={}, txt = "Favourite")
        SortButton(onClick ={}, txt = "Recent")
        SortButton(onClick ={}, txt = "Costum")
    }
}
@Composable
fun SortButton(onClick: () -> Unit, txt: String) {
    Box(Modifier.clickable(onClick = onClick)) {
        Text(
            text = txt,
            fontSize = 25.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.bebas_neue)), //hay que incluirlo en otro lugar todo
            modifier = Modifier.padding(6.dp),
        )
    }
}

@Composable
fun RoutineCardDetails(title: String) {
    Text(
        text = "Lorum ipsum dorum Lorum ipsum doru Lorum ipsum doru Lorum ipsum doru Lorum ipsum",
        fontSize = 25.sp,
        color = Green,
        fontFamily = FontFamily(Font(R.font.bebas_neue)), //hay que incluirlo en otro lugar todo
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(0.8f),
        textAlign = TextAlign.Justify
    )
}

@Composable
fun RoutineCardTitle(title: String, iconId: Int) {
    Row (verticalAlignment = Alignment.CenterVertically) {
        Text(
                text = title,
                fontSize = 50.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.bebas_neue)), //hay que incluirlo en otro lugar todo
                modifier = Modifier,
                textAlign = TextAlign.Start
            )
            Image(
                painter = painterResource(iconId),
                contentDescription = null,
                modifier = Modifier
                    .scale(2F)
                    .weight(2f) //esto sirve como un padding pero mas responsivo
            )
        }
}

@Composable
fun RoutineCard(backgroundImageId: Int, iconId: Int, title: String) {

    var expanded by rememberSaveable { mutableStateOf(false) }
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

            RoutineCardTitle(title = title, iconId = iconId)
            if (expanded)
                RoutineCardDetails(title = "")
        }

    }

    Spacer(modifier = Modifier.height(30.dp))


}

@Composable
fun MyRoutines(){
    Column (
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.my_routines),
            fontSize = 70.sp,
            color = Green,
            fontFamily = FontFamily(Font(R.font.bebas_neue)), //hay que incluirlo en otro lugar todo
        )

        Spacer(modifier = Modifier.height(10.dp))
        SortButtons()

        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(20) {
                RoutineCard(
                    backgroundImageId = R.drawable.registration_background,
                    iconId = R.drawable.star_outline_white,
                    "Routine #$it"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyRoutinesPreview() {
    MyRoutines();
}
