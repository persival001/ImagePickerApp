package com.persival.imagepickerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.persival.imagepickerapp.ui.theme.ImagePickerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImagePickerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PhotoPicker()
                }
            }
        }
    }
}

@Composable
fun PhotoPicker() {
    val config = LocalConfiguration.current
    val height = config.screenHeightDp
    val width = config.screenHeightDp
    val surfaceHeight = height * 0.75
    val surfaceWidth = width * 0.9

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Surface(
            modifier = Modifier
                .height(surfaceHeight.dp)
                .width(surfaceWidth.dp)
        ) {

        }
        Button(
            onClick = {}) {
            Text(text = "Choisir une image")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImagePickerAppTheme {
        PhotoPicker()
    }
}