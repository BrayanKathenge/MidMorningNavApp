package com.example.midmorningnavigationapp.ui.theme.pages.Products

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
import com.example.midmorningnavigationapp.data.ProductRepository
import com.example.midmorningnavigationapp.navigation.ROUTE_LOGIN
import com.example.midmorningnavigationapp.ui.theme.MidMorningNavigationAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductsScreen(navController: NavHostController) {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context= LocalContext.current
        Text(
            text = "Add Products",
            color = Color.Blue,
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold)


        var productname by remember { mutableStateOf(TextFieldValue("")) }
        var productqty by remember { mutableStateOf(TextFieldValue("")) }
        var productprice by remember { mutableStateOf(TextFieldValue("")) }


        OutlinedTextField(value = productname, onValueChange = {productname =it},
            label = { Text(text = "Product_name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = productqty,
            onValueChange = {productqty=it},
            label = { Text(text = "Quantity *") },
            keyboardOptions = KeyboardOptions (keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = productprice, onValueChange = {productprice =it},
            label = { Text(text = "price *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))



        Button(onClick = {
            //--Save logic here--//
            var productRepository = ProductRepository(navController, context)
           productRepository.SaveProduct(productname.text.trim(),productqty.text.trim(),productprice.text.trim())
        }) {
            Text(
                text = "Save",
            )

        }
        Spacer(modifier = Modifier.height(20.dp))


    }

}


@Preview
@Composable
fun AddProductScreenPreview() {
    MidMorningNavigationAppTheme {
        AddProductsScreen(rememberNavController())}
    }

