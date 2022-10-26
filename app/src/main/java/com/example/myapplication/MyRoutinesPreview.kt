package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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


@Composable
fun RoutineCard(backgroundImageId: Int, iconId: Int, onClickCard: () -> Unit, title: String) {

    Spacer(modifier = Modifier.height(30.dp))

    Box (
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = onClickCard),
        contentAlignment = Alignment.Center,
        ){
        Image(
            painter = painterResource(backgroundImageId),
            contentDescription = null,
            modifier = Modifier.width(370.dp).clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = title,
            fontSize = 50.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.bebas_neue)), //hay que incluirlo en otro lugar todo
            modifier = Modifier.align(Alignment.CenterStart).padding(start = 35.dp)
        )
        Image(
            painter = painterResource(iconId),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterEnd).padding(end = 60.dp).scale(2F)
        )
    }
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
            fontSize = 80.sp,
            color = Green,
            fontFamily = FontFamily(Font(R.font.bebas_neue)), //hay que incluirlo en otro lugar todo
        )

        LazyColumn {
            items(20) {
                RoutineCard(
                    backgroundImageId = R.drawable.registration_background,
                    iconId = R.drawable.star_outline_white,
                    {},
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
