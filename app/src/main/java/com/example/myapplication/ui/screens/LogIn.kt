package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.view.KeyEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.Button1
import com.example.myapplication.ui.screens.BackgroundImage
import com.example.myapplication.ui.theme.Black
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.theme.Purple500
import com.example.myapplication.viewmodel.RoutinesViewModel
import com.example.myapplication.viewmodel.UserViewModel


@Composable
fun LogIn(viewModel: UserViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), actionRedirect: () -> Unit, backButton: () -> Unit){

    var passWord by remember { mutableStateOf(TextFieldValue("")) }
    var snackbar by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val focusManager = LocalFocusManager.current

    //LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    BackgroundImage(painter = painterResource(id = R.drawable.log_in_photo))
    Column(verticalArrangement = Arrangement.SpaceEvenly) {
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
                    .scale(1.5F)
                    .align(CenterHorizontally)
            )
            EmailTextField(
                onTextChange = {/*TODO AGREGAR EL MODIFICADOR DEL STORE*/ },
                modifier = Modifier.align(CenterHorizontally)
            )
            PasswordTextField(
                onTextChange = {},
                modifier = Modifier.align(CenterHorizontally)
            )
            Button1(
                fontSize = 23,
                text = "Log In",
                handler = {if(viewModel.loginAttempt("", "")) actionRedirect() else snackbar = true},
                modifier = Modifier.align(CenterHorizontally)
            )

            LinkedText(
                handler = {/*TODO AGREGAR EL MODIFICADOR DEL STORE*/ },
                modifier = Modifier.align(CenterHorizontally)
            )

        }
    }

}
@Composable
fun EmailTextField(onTextChange :(TextFieldValue) -> Unit,modifier: Modifier = Modifier){
    var email by remember { mutableStateOf(TextFieldValue("")) }
    TextField( value = email,
        onValueChange = { newText ->
            email = newText
            onTextChange(newText);
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
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
fun PasswordTextField(onTextChange :(TextFieldValue) -> Unit,modifier: Modifier = Modifier){
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisible by remember { mutableStateOf(false) }
    TextField( value = email,
        onValueChange = { newText ->
            email = newText
            onTextChange(newText);
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
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
fun LinkedText(handler : (Int) -> Unit,modifier: Modifier = Modifier){
    val mAnnotatedLinkString = buildAnnotatedString {
        // creating a string to display in the Text
        val mStr = "Forgot password?"
        append(mStr)
        addStyle(
            style = SpanStyle(
                color = Color.White,
                textDecoration = TextDecoration.Underline
            ), start = 0, end = mStr.length
        )

    }
    ClickableText(text = mAnnotatedLinkString, onClick = handler,modifier = modifier)

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

