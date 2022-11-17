package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.components.Button2
import com.example.myapplication.ui.theme.*

@Composable
fun ApiErrorScreen(){

    Box(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().background(backGround),
    ){

        Column(
            modifier = Modifier.fillMaxWidth(0.8F).background(Gray, RoundedCornerShape(30.dp)).align(
                Alignment.Center).border(3.dp, Color.Red, RoundedCornerShape(20.dp))
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.error_header),
                //style = MaterialTheme.typography.h1
                fontFamily = H1Font,
                fontSize = 30.sp,
                color = Green,
                modifier = Modifier.padding(horizontal = 10.dp).align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.error_msg),
                //style = MaterialTheme.typography.h3
                fontFamily = NormalFont,
                fontSize = 17.sp,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 20.dp).align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
//            Button2(
//                fontSize = 20,
//                text = stringResource(R.string.error_btn),
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
            Spacer(modifier = Modifier.height(20.dp))
        }

    }

}


@Preview
@Composable
fun testError(){
    ApiErrorScreen()
}