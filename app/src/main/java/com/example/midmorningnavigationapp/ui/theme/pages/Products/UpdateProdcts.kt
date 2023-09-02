package com.example.midmorningnavigationapp.ui.theme.pages.Products

import android.widget.Toast
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
import com.example.midmorningnavigationapp.data.ProductRepository
import com.example.midmorningnavigationapp.models.Product
import com.example.midmorningnavigationapp.ui.theme.MidMorningNavigationAppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductsScreen(navController: NavHostController, id:String) {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context= LocalContext.current
        var name by remember {
            mutableStateOf("")
        }
        var qty by remember {
            mutableStateOf("")
        }
        var price by remember {
            mutableStateOf("")
        }
        var UpdateRef = FirebaseDatabase.getInstance().getReference().child("Products/$id")
        UpdateRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var product=snapshot.getValue(Product::class.java)
                name=product!!.name
                qty=product!!.quantity
                price=product!!.price
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })

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
            //--Update logic here--//
            var productRepository = ProductRepository(navController, context)
            productRepository.Uproduct(productname.text.trim(),productqty.text.trim(),productprice.text.trim(),id)
        }) {
            Text(
                text = "Update",
            )

        }
        Spacer(modifier = Modifier.height(20.dp))


    }

}


@Preview
@Composable
fun UpdateProductScreenPreview() {
    MidMorningNavigationAppTheme {
        UpdateProductsScreen(rememberNavController(), id ="")}
}
