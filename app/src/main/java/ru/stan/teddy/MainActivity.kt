package ru.stan.teddy

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import ru.stan.teddy.ui.theme.TeddyTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val currentScreen = remember { mutableStateOf("Home") }

            TeddyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(stringResource(R.string.app_name)) },
                                actions = {
                                    IconButton(onClick = { /* действие для первой кнопки */ }) {
                                        Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                                    }
                                    IconButton(onClick = { /* действие для второй кнопки */ }) {
                                        Icon(Icons.Default.Search, contentDescription = "Search")
                                    }
                                    IconButton(onClick = { /* действие для третьей кнопки */ }) {
                                        Icon(Icons.Default.Share, contentDescription = "Share")
                                    }
                                    IconButton(onClick = { /* действие для четвертой кнопки */ }) {
                                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                                    }
                                }
                            )
                        },
                        bottomBar = {
                            BottomNavigationBar(
                                navController = navController,
                                currentScreen = currentScreen
                            )
                        }
                    ) {
                        Navigation(navController = navController, currentScreen = currentScreen)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, currentScreen: MutableState<String>) {
    val screens = listOf("Home", "HelloAbc", "PreK", "Kcource", "Chat")
    BottomNavigation {
        screens.forEach { screen ->
            BottomNavigationItem(
                selected = currentScreen.value == screen,
                onClick = {
                    currentScreen.value = screen
                    navController.navigate(screen)
                },
                icon = {
                    when (screen) {
                        "Home" -> Icon(Icons.Default.Home, contentDescription = "Home")
                        "HelloAbc" -> Icon(Icons.Default.Home, contentDescription = "HelloAbc")
                        "PreK" -> Icon(Icons.Default.Phone, contentDescription = "PreK")
                        "Kcource" -> Icon(Icons.Default.Phone, contentDescription = "Kcource")
                        "Chat" -> Icon(Icons.Default.Phone, contentDescription = "Chat")
                    }
                },
                label = { Text(screen) }
            )
        }
    }
}

@Composable
fun Navigation(navController: NavController, currentScreen: MutableState<String>) {
    NavHost(navController as NavHostController, startDestination = "Home") {
        composable("Home") { HomeScreen() }
        composable("HelloAbc") { HelloAbc() }
        composable("PreK") { PreK() }
        composable("Kcource") { Kcource() }
        composable("Chat") { Chat() }
    }
}
