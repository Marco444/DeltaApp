package com.example.myapplication.ui.components

import androidx.compose.foundation.Image
import Base64BitMap
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel


const val ROUTINE_CARD_WIDTH = 370;

sealed class RoutineCard(val iconClicked: ImageVector, val iconUnClicked: ImageVector, val description: String) {
    object MyRoutine: RoutineCard ( Icons.Default.Favorite, Icons.Outlined.FavoriteBorder, "Go")
    object Progress: RoutineCard (Icons.Default.Favorite, Icons.Outlined.FavoriteBorder, "See Progress")
    object ExploreRoutine: RoutineCard (  Icons.Default.BookmarkAdded, Icons.Outlined.Add, "Share")
}

@Composable
fun RoutineCardDetails( routine: Routines, buttonText: String, buttonHandler: () -> Unit) {
    Text(
        text = routine.description,
        fontSize = 25.sp,
        color = Green,
        modifier = Modifier
    )
    Row (modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Stars(routine = routine) 
        Button1(fontSize = 16, text = buttonText, handler = buttonHandler)
    }
}


private fun shareRoutine(context: Context, title: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Routine")
        putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_msg) + "http://deltapp.com")
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            "Share routine"
        )
    )
}

@Composable
fun RoutineCardTitle(title: String, iconId: ImageVector, clickedIcon: () -> Unit = {}, id: Int) {


    val context = LocalContext.current

    Row ( horizontalArrangement  =  Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = title,
            fontSize = 50.sp,
            color = Color.White,
            textAlign = TextAlign.Start,
            modifier = Modifier.wrapContentSize()
        )

        Spacer(modifier = Modifier.width(10.dp))
        IconButton(onClick = { shareRoutine(context, title) }) {
            Icon(Icons.Default.Share, contentDescription = "share icon", tint = Color.White)
        }
        Icon(
            iconId,
            contentDescription = null,
            modifier = Modifier
                .clickable(onClick = clickedIcon)
                .scale(1.5f)
                .padding(end = 5.dp),
            tint = Color.White,
        )


    }
}

@Composable
fun BackgroundImageCard(routine: Routines, imageHeight: Dp) {

    if(routine.img == "")
        Image(
            painter = painterResource( R.drawable.registration_background),
            contentDescription = "Routine Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(ROUTINE_CARD_WIDTH.dp)
                .clip(RoundedCornerShape(20.dp))
                .height(imageHeight),
        )
    else {
        val bitmap = Base64BitMap(routine.img)?.asImageBitmap()
        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = "Routine Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(ROUTINE_CARD_WIDTH.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .height(imageHeight),
            )
        }
    }}

@Composable
fun RoutineCard(routine: Routines, iconId: ImageVector, clickedIcon: () -> Unit = {}, actionHandler: () -> Unit = {}, routineCard: RoutineCard, viewModel: RoutinesViewModel) {

    var expanded by remember { mutableStateOf(!viewModel.cardsExpandable()) }
    var imageHeight by remember { mutableStateOf(if(!viewModel.cardsExpandable()) 200.dp else 70.dp ) }

    Box (
        Modifier
            .width(ROUTINE_CARD_WIDTH.dp)
            .clickable {
                expanded = if (viewModel.cardsExpandable()) !expanded else true
                imageHeight = if (expanded) 200.dp else 70.dp
            },
        contentAlignment = Alignment.Center
    ){
        BackgroundImageCard(routine, imageHeight)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RoutineCardTitle(
                title = routine.title,
                iconId = iconId,
                clickedIcon = { clickedIcon() },
                id = routine.id
            )
            if (expanded)
                RoutineCardDetails(routine = routine,
                                    buttonHandler = actionHandler,
                                    buttonText = routineCard.description)

        }


    }

    Spacer(modifier = Modifier.height(30.dp))


}
