package com.example.abbystowerapp


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.abbystowerapp.Navigation.AppNavigation
import com.example.abbystowerapp.ui.theme.AbbysTowerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= 37) {
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { }
                .launch("android.permission.ACCESS_LOCAL_NETWORK")
        }
        setContent {
            AbbysTowerAppTheme {
                AppNavigation()
            }
        }
    }
}
