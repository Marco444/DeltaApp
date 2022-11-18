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
import androidx.compose.material.*
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.classes.Routines
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel
import com.example.myapplication.ui.navigation.NavBarScreen
import com.example.myapplication.ui.theme.Gray


const val ROUTINE_CARD_WIDTH = 370;

sealed class RoutineCard(val iconClicked: ImageVector, val iconUnClicked: ImageVector, val description: String) {
    object MyRoutine: RoutineCard ( Icons.Default.Favorite, Icons.Outlined.FavoriteBorder, "Start")
    object Progress: RoutineCard (Icons.Default.Favorite, Icons.Outlined.FavoriteBorder, "See Progress")
    object ExploreRoutine: RoutineCard (  Icons.Default.Favorite, Icons.Outlined.FavoriteBorder, "Preview")
}

@Composable
fun RoutineCardDetails( routine: Routines, buttonText: String, buttonHandler: () -> Unit) {
    Text(
        text = routine.description,
        fontSize = 25.sp,
        color = Green,
        modifier = Modifier.fillMaxWidth(0.85f)
    )
    Row (verticalAlignment = Alignment.CenterVertically) {
        Stars(routine = routine, clickable = false)
        Button1(fontSize = 16, text = buttonText, handler = buttonHandler)
    }
}


private fun shareRoutine(context: Context, routine: Routines) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Routine: " + routine.title)
        putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_msg) + "http://deltapp.com/" + routine.id)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            "Share routine"
        )
    )
}

@Composable
fun CardOptions(viewModel: RoutinesViewModel, routine: Routines) {

    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }

    Icon(
        Icons.Default.MoreVert,
        contentDescription = null,
        modifier = Modifier
            .clickable(onClick = { expanded = true })
            .scale(1.5f)
            .padding(end = 5.dp),
        tint = Color.White,
    )
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
        .background(
            Color.White
        )
        .clip(RoundedCornerShape(8.dp))) {

        if(routine.isPublic) {
            DropdownMenuItem(onClick = { expanded = false; shareRoutine(context, routine) }) {
                Text(text = "Share")
            }
        }
        DropdownMenuItem(onClick = { expanded = false ; viewModel.deleteRoutine(routine.id)} ) {
            Text(text = "Delete")
        }
    }
}


@Composable
fun RoutineCardTitle(routineCard: RoutineCard, routine: Routines, iconId: ImageVector, clickedIcon: () -> Unit = {}, viewModel: RoutinesViewModel) {


    val context = LocalContext.current

    Row ( horizontalArrangement  =  Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = routine.title,
            fontSize = 50.sp,
            color = Color.White,
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width((ROUTINE_CARD_WIDTH - 100).dp)
        )

        Spacer(modifier = Modifier.width(10.dp))
            Icon(
                iconId,
                contentDescription = null,
                modifier = Modifier
                    .clickable(onClick = clickedIcon)
                    .scale(1.5f)
                    .padding(end = 5.dp),
                tint = Color.White,
            )

        if(routineCard == RoutineCard.ExploreRoutine) {
            Icon(
                Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier
                    .clickable(onClick = { shareRoutine(context, routine) })
                    .padding(end = 5.dp),
                tint = Color.White,
            )
        } else {
            CardOptions(viewModel = viewModel, routine = routine)
        }

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
            alpha = 0.4f
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
                alpha = 0.4f
            )
        }
    }}

@Composable
fun RoutineCard(routine: Routines, iconId: ImageVector, clickedIcon: () -> Unit = {}, actionHandler: () -> Unit = {}, routineCard: RoutineCard, viewModel: RoutinesViewModel, buttonText: String) {

    var expanded by remember { mutableStateOf(!viewModel.cardsExpandable()) }
    var imageHeight by remember { mutableStateOf(if (!viewModel.cardsExpandable()) 200.dp else 70.dp) }

    Box(
        Modifier
            .width(ROUTINE_CARD_WIDTH.dp)
            .clickable {
                expanded = if (viewModel.cardsExpandable()) !expanded else true
                imageHeight = if (expanded) 200.dp else 70.dp
            }
            .background(Gray, RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        val showImage by viewModel.displayRoutineImages.collectAsState()

        if(showImage) {
            BackgroundImageCard(routine, imageHeight)
        }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                RoutineCardTitle(
                    routineCard = routineCard,
                    routine = routine,
                    iconId = iconId,
                    clickedIcon = { clickedIcon() },
                    viewModel = viewModel
                )
                if (expanded)
                    RoutineCardDetails(
                        routine = routine,
                        buttonHandler = actionHandler,
                        buttonText = buttonText
                    )

            }

    }

    Spacer(modifier = Modifier.height(30.dp))


}
