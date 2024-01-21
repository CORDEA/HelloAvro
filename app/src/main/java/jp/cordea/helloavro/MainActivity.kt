package jp.cordea.helloavro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import jp.cordea.helloavro.ui.Main

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}
