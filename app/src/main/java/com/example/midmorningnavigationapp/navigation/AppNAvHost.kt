package com.example.midmorningnavigationapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.midmorningnavigationapp.ui.theme.pages.Products.AddProductScreenPreview
import com.example.midmorningnavigationapp.ui.theme.pages.Products.AddProductsScreen
import com.example.midmorningnavigationapp.ui.theme.pages.Products.UpdateProductsScreen
import com.example.midmorningnavigationapp.ui.theme.pages.Products.ViewProductsScreen
import com.example.midmorningnavigationapp.ui.theme.pages.about.AboutScreen
import com.example.midmorningnavigationapp.ui.theme.pages.home.HomeScreen
import com.example.midmorningnavigationapp.ui.theme.pages.login.LoginScreen

@Composable
fun AppNavHost (
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination:String = ROUTE_LOGIN) {
    NavHost(navController = navController,
        modifier = modifier,startDestination= startDestination ){
       composable(ROUTE_LOGIN){
           LoginScreen(navController)
       }
        composable(ROUTE_ABOUT){
           AboutScreen(navController)
       }
        composable(ROUTE_HOME){
          HomeScreen(navController)
       }
        composable(ROUTE_ADD_PRODUCTS){
            AddProductsScreen(navController)

       }
        composable(ROUTE_VIEW_PRODUCTS){
            ViewProductsScreen(navController)

       }
        composable(ROUTE_UPDATE_PRODUCTS+"/id"){passedData->
            UpdateProductsScreen(navController,passedData.arguments?.getString("id")!! )

       }

    }


}