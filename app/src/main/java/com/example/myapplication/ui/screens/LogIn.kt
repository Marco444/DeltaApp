package com.example.myapplication.ui.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.KeyEvent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.activities.mainactivity.UserViewModel
import com.example.myapplication.ui.components.Button1
import com.example.myapplication.ui.theme.Green


@Composable
fun LogIn(actionRedirect: (Int) -> Unit, backButton: () -> Unit, viewModel: UserViewModel, routineId: Int){

    var passWord by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var snackbar by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val focusManager = LocalFocusManager.current
    val uiState by viewModel.userState.collectAsState()
    val auth by uiState.isAuthenticated.collectAsState()
    // For the snackbar
    val (snackbarVisibleState, setSnackBarState) = remember { mutableStateOf(false) }


    val gradient = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
    )

    //LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    BackgroundImage(painter = painterResource(id = R.drawable.log_in_photo))
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(gradient)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()))
        {
            Button(
                onClick = backButton,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Green),
                modifier = Modifier
                    .padding(start = 18.dp, top = 15.dp)
                    .size(55.dp)
            )
            {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "content description",
                    tint = Color.Black
                )
            }


        }
        Column(
            modifier = Modifier
                .height(500.dp)
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
            ,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(R.drawable.delta_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.3F)
                    .align(CenterHorizontally)
            )
            EmailTextField(
                onTextChange = {email = it },
                modifier = Modifier.align(CenterHorizontally),
                email = email
            )
            PasswordTextField(
                onTextChange = {passWord = it},
                modifier = Modifier.align(CenterHorizontally),
                password = passWord
            )
            Button1(
                fontSize = 23,
                text = stringResource(id = R.string.login),
                handler = {
                    viewModel.login(email.text, passWord.text )

                    },
                modifier = Modifier.align(CenterHorizontally)
            )

            LaunchedEffect(key1 = auth){
                if(auth) {
                    setSnackBarState(false)
                    actionRedirect(routineId)
                }
            }
            LaunchedEffect(key1 = uiState.errorBoolean){
                if(uiState.errorBoolean == true)
                    setSnackBarState(true)
            }
        }
    }

    // The Snackbar
    if (snackbarVisibleState) {
        Snackbar(
            action = {
                Button(
                    onClick = {setSnackBarState(!snackbarVisibleState)},
                ) {
                    Text(text = "Dismiss")
                }
            },
            modifier = Modifier.padding(8.dp)

        ) { Text(text = stringResource(id = R.string.login_failed)) }
    }

}
@Composable
fun EmailTextField(onTextChange :(TextFieldValue) -> Unit,modifier: Modifier = Modifier,email:TextFieldValue){
    TextField( value = email,
        onValueChange = onTextChange,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor =  Color.Transparent),
        label = {
            Text(text = "Email")
        },
        modifier = modifier.onKeyEvent {
            if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER){
                true
            }
            false
        },
        textStyle = TextStyle.Default.copy(fontSize = 15.sp)
    )
}
@Composable
fun PasswordTextField(onTextChange :(TextFieldValue) -> Unit,modifier: Modifier = Modifier,password:TextFieldValue){
    var passwordVisible by remember { mutableStateOf(false) }
    TextField( value = password,
        onValueChange = onTextChange,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor =  Color.Transparent),
        label = {
            Text(text = "Password")
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"
            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(imageVector  = image, description)
            }
        },
        modifier = modifier,
        textStyle = TextStyle.Default.copy(fontSize = 15.sp)
    )
}
@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

