package com.example.foodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
   // override fun onSupportNavigateUp(): Boolean {
   //     val navController = findNavController(R.id.fragment)
   //     return navController.navigateUp() || super.onSupportNavigateUp()
  //  }

}