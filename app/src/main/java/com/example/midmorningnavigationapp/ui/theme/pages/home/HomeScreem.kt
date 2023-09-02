package com.example.midmorningnavigationapp.ui.theme.pages.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.midmorningnavigationapp.navigation.ROUTE_ADD_PRODUCTS
import com.example.midmorningnavigationapp.navigation.ROUTE_VIEW_PRODUCTS
import com.example.midmorningnavigationapp.ui.theme.MidMorningNavigationAppTheme

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
         ) {
        Text(text = "Welcome to HomeScreen")

        Button(onClick = { navController.navigate(ROUTE_ADD_PRODUCTS) }) {
            Text(text = "Add Product")

        }
        Button(onClick = { navController.navigate(ROUTE_VIEW_PRODUCTS) }) {
            Text(text = "View Products")

        }

    }

}

@Preview
@Composable
fun HomeScreenPreview() {
    MidMorningNavigationAppTheme {
        HomeScreen(rememberNavController())
    }

}