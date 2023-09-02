package com.example.midmorningnavigationapp.data

import android.app.ProgressDialog
import android.content.Context
import android.provider.Settings
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.midmorningnavigationapp.models.Product
import com.example.midmorningnavigationapp.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.contracts.contract

class ProductRepository(navController: NavHostController,var context: Context) {
    var authRepository: AuthRepository
    var progress: ProgressDialog
    var products: ArrayList<Product>

    init {
        authRepository = AuthRepository(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("loading")
        progress.setMessage("please wait ...")
        products = mutableListOf<Product>() as ArrayList<Product>
    }

    fun SaveProduct(name: String, quantity: String, price: String) {
        var id = System.currentTimeMillis().toString()
        var productData = Product(name, quantity, price, id)
        var productRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
        progress.show()
        productRef.setValue(productData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "saved successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error Try Again", Toast.LENGTH_SHORT).show()
            }
        }

    }


    fun viewProduct(): ArrayList<Product> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Products")
        progress.show()
        ref.addValueEventListener(object :ValueEventListener{  override fun onDataChange(snapshot: DataSnapshot) {
            progress.dismiss()
            products.clear()
            for (snap in snapshot.children) {
                var product = snap.getValue(Product::class.java)
                products.add(product!!)
            }
        }

            override fun onCancelled(error: DatabaseError) {
             progress.dismiss()
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }



        })
        return products


    }


    fun deleteproduct(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference().child("Products/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }}


        fun Uproduct(name:String,Quantity:String,Price:String,id: String) {
            var UpdateRef = FirebaseDatabase.getInstance().getReference().child("Products/$id")
            var productData=Product(name,Price,id,Quantity)
            progress.show()
            UpdateRef.setValue(productData).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful){
                    Toast.makeText(context, "Update Successful", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


