package ru.andrewkir.testingsaturday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import ru.andrewkir.testingsaturday.presentation.NavGraphs
import ru.andrewkir.testingsaturday.presentation.theme.TestingSaturdayTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()
    setContent {
      TestingSaturdayTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Column(modifier = Modifier.padding(innerPadding)) {
            DestinationsNavHost(navGraph = NavGraphs.root)
          }
        }
      }
    }
  }
}