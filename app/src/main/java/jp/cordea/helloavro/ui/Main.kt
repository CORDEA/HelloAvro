package jp.cordea.helloavro.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.cordea.helloavro.ui.theme.HelloAvroTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

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
private fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(navController = navController)
        }
        composable("details/{id}") { entry ->
            val id = requireNotNull(entry.arguments?.getString("id")).toInt()
            Details(
                viewModel = koinViewModel(parameters = { parametersOf(id) }),
                navController = navController,
            )
        }
    }
}
