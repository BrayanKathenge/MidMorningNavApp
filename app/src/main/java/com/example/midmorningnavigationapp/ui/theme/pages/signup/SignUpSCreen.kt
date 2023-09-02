package com.example.midmorningnavigationapp.ui.theme.pages.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.midmorningnavigationapp.data.AuthRepository
import com.example.midmorningnavigationapp.navigation.ROUTE_LOGIN
import com.example.midmorningnavigationapp.navigation.ROUTE_SIGNUP
import com.example.midmorningnavigationapp.ui.theme.MidMorningNavigationAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController) {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
         var context= LocalContext.current
        Text(
            text = "Register Today",
            color = Color.Blue,
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold)


        var name by remember { mutableStateOf(TextFieldValue("")) }
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var password by remember { mutableStateOf(TextFieldValue("")) }


        OutlinedTextField(value = name, onValueChange = {name =it},
            label = {Text(text = "username *")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {email=it},
            label = { Text(text = "email *")},
            keyboardOptions = KeyboardOptions (keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = password, onValueChange = {password =it},
            label = {Text(text = "password *")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(20.dp))



        Button(onClick = {
            //--Signup logic here--//
            var authRepository = AuthRepository(navController, context)
            authRepository.signup(name.text.trim(),email.text.trim(), password.text.trim())
        }) {
            Text(
                text = "SignUp",
            )

        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {  navController.navigate(ROUTE_LOGIN)}) {
            Text(text = "Have account? Login")

        }


    }

}


@Preview
@Composable
fun SignUpScreenPreview() {
    MidMorningNavigationAppTheme {
       SignUpScreen(rememberNavController())
    }

}