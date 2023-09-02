package com.example.midmorningnavigationapp.ui.theme.pages.Products

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.midmorningnavigationapp.data.ProductRepository
import com.example.midmorningnavigationapp.navigation.ROUTE_UPDATE_PRODUCTS
import com.example.midmorningnavigationapp.ui.theme.MidMorningNavigationAppTheme

@Composable
fun ViewProductsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "All Products",
            color = Color.Yellow,
            fontFamily = FontFamily.Cursive,
            fontSize = 30.sp


        )
        Spacer(modifier = Modifier.height(20.dp))

        var context = LocalContext.current
        var productRepository = ProductRepository(navController, context)
        var products = productRepository.viewProduct()
        LazyColumn() {
            items(products.count()) {
                var productName = products.get(it).name
                var productQty = products.get(it).quantity
                var productPrice = products.get(it).price
                var id = products.get(it).id
                ProductItem(
                    name = productName,
                    qty = productQty,
                    price = productPrice,
                    id = id,
                    productRepository =productRepository ,
                    navController = navController

                )



            }


        }
    }
}

@Composable
fun ProductItem(name:String,qty:String,price:String,id:String, productRepository: ProductRepository,navController: NavHostController) {
    Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = name)
            Text(text = qty)
            Text(text = price)
        Button(onClick = { productRepository.deleteproduct(id) }) {
            Text(text = "Delete")

        }
        Button(onClick = { navController.navigate(ROUTE_UPDATE_PRODUCTS) }) {
            Text(text = "Update")
        }
    }

}



@Preview
@Composable
fun ViewproductPreview() {
    MidMorningNavigationAppTheme {
        ViewProductsScreen(rememberNavController())
    }
}















