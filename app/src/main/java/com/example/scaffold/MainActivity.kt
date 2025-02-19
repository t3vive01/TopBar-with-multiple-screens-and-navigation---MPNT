package com.example.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.scaffold.ui.theme.ScaffoldTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController

import androidx.navigation.NavController
import androidx.navigation.NavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScaffoldTheme {
                ScaffoldApp()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(title:String,navController : NavController){
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { /* do something */}) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Open menu"
                )
            }
        },
        actions = {
            IconButton(onClick = { expanded =! expanded }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Open submenu"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = { navController.navigate("info") }
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = { navController.navigate("settings") }
                )
            }
        },
    )
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopAppBar("My First App",navController) },
    ) {
       innerPadding ->
        Text(text="Home Screen",modifier=Modifier.padding(innerPadding))
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title:String,navControler:NavController){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {navControler.navigateUp()}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
    )
}
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Info",navController) },
    ) {
            innerPadding ->
        Text(text="Info Screen",modifier=Modifier.padding(innerPadding))
}}
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Settings",navController) },
    ) {
            innerPadding ->
        Text(text="Settings Screen",modifier=Modifier.padding(innerPadding))
    }
    }
@Composable
fun ScaffoldApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            MainScreen(navController)
        }
        composable("info"){
            InfoScreen(navController)
            }
        composable("settings"){
            SettingsScreen(navController)
        }
    }
}



