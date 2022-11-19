package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.activities.secondactivity.RoutinesViewModel

@Composable
fun SearchField(viewModel: RoutinesViewModel) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current
    TextField(
        value = text,
        onValueChange = { newText ->
            if (newText.text.isNotEmpty() && newText.text.last() == '\n'){
                focusManager.clearFocus()
                if(text.text.length >=  3)
                    viewModel.getExploreWithParamsWrapper(text.text)
            }else{
                text = newText
            }
            if(newText.text.isEmpty()){
                viewModel.getExploreWithParamsWrapper(null)
            }
        },
        shape = RoundedCornerShape(8.dp),
        trailingIcon = {
            IconButton(onClick = {
                if(text.text.length >= 3)
                    viewModel.getExploreWithParamsWrapper(text.text)
                else
                    viewModel.getExploreWithParamsWrapper(null)

            }) {
                Icon(Icons.Filled.Search, "")
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor =  Color.Transparent),
        textStyle = TextStyle.Default.copy(fontSize = 15.sp),
        modifier = Modifier
            .height(55.dp).widthIn(min = 300.dp, max = 600.dp)
    )
}

@Composable
fun FilterButton(viewModel: RoutinesViewModel) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(
            onClick = { expanded = !expanded},
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .height(40.dp)
                .widthIn(min = 50.dp, max = 100.dp)
        ) {
            Icon(Icons.Filled.Sort, contentDescription = null)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier
            .background(
                Color.White
            )
            .clip(RoundedCornerShape(8.dp))) {
            DropdownMenuItem(onClick = { viewModel.getExploreRoutines() ; expanded = false }) {
                Text(text = stringResource(id = R.string.all))
            }
            DropdownMenuItem(onClick = { viewModel.getUserFavoritesWrapper() ; expanded = false }) {
                Text(text = stringResource(id = R.string.favourite))
            }
        }
    }
}

@Composable
fun SearchAndFilter(viewModel: RoutinesViewModel) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        SearchField(viewModel)
        Spacer(Modifier.width(10.dp))
        FilterButton(viewModel = viewModel)
    }
}
