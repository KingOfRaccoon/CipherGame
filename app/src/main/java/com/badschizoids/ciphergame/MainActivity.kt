package com.badschizoids.ciphergame

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.badschizoids.ciphergame.network.DataFireStore
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var navView: BottomNavigationView
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.nav_host_fragment)
        navView = findViewById(R.id.navView)
        val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.mainActionFragment, R.id.chatFragment
                )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) // проверка на наличие разрешений
            requestPermissions(arrayOf(Manifest.permission.INTERNET), 101)
        DataFireStore().getAllMemes().addOnCompleteListener {
            if (it.isSuccessful){
                val list = it.result?.get("mem") as List<String>
                list.forEach {
                    Log.e("Test", it)
                }
            }
            else
                Log.e("Data", it.exception?.message.toString())
        }
    }


    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()
}