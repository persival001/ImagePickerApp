package com.persival.imagepickerapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
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

    // URI picture type
    var image by remember { mutableStateOf<Uri?>(null) }

    // Contract
    val contract = ActivityResultContracts.GetContent()

    // Type of file to obtain
    val type = "image/*"

    // ImagePicker setup
    val imageLauncher = rememberLauncherForActivityResult(
        contract = contract,
        onResult = { it ->
            image = it
        })

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Surface(
            modifier = Modifier
                .height(surfaceHeight.dp)
                .width(surfaceWidth.dp)
                .padding(20.dp),
            border = BorderStroke(1.dp, Color.Black),
            contentColor = Color.Black,
            shadowElevation = 8.dp,
            shape = RoundedCornerShape(percent = 12)
        ) {

            if (image != null) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest
                            .Builder(LocalContext.current)
                            .data(image)
                            .build()
                    ),
                    contentDescription = image.toString(),
                    contentScale = ContentScale.Crop
                )

            }
        }
        Button(
            onClick = {
                //Take a picture
                imageLauncher.launch(type)
            }) {
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