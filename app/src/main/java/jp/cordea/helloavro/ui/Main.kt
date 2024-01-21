package jp.cordea.helloavro.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.cordea.helloavro.ui.theme.HelloAvroTheme

@Composable
fun Main() {
    HelloAvroTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavGraph()
        }
    }
}

@Composable
private fun NavGraph() {
    NavHost(navController = rememberNavController(), startDestination = "home") {
        composable("home") {
            Home()
        }
    }
}
