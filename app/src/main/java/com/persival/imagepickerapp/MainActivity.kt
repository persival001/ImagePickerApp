package com.persival.imagepickerapp

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.persival.imagepickerapp.ui.theme.ImagePickerAppTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val granted = permissions.entries.all { it.value }
            if (granted) {
                Log.d("Permissions", "GRANTED")
            } else {
                Log.d("Permissions", "REFUSED")
            }
        }

        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_MEDIA_LOCATION
            )
        )

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

    // URI for captured picture
    var imageCaptureUri by remember { mutableStateOf<Uri?>(null) }

    // URI picture type
    var image by remember { mutableStateOf<Uri?>(null) }

    // Contract
    val contract = ActivityResultContracts.GetContent()

    // Type of file to obtain
    val type = "image/*"

    // Contract for capture picture
    val takePictureLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            // La photo a été prise avec succès, mettez à jour l'URI de l'image pour afficher l'image
            image = imageCaptureUri
        }
    }

// Prepare URI of captured picture before launch intent of camera
    val context = LocalContext.current
    imageCaptureUri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        createImageFile(context)
    )


    // ImagePicker setup
    val imageLauncher = rememberLauncherForActivityResult(
        contract = contract,
        onResult = {
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

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    imageLauncher.launch(type)
                }
            ) {
                Text("Choisir une image")
            }

            Button(
                onClick = {
                    takePictureLauncher.launch(imageCaptureUri)
                }
            ) {
                Text("Prendre une photo")
            }
        }

    }
}

// Create image file from camera to storage directory
fun createImageFile(context: Context): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir: File = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
    return File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        storageDir
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImagePickerAppTheme {
        PhotoPicker()
    }
}