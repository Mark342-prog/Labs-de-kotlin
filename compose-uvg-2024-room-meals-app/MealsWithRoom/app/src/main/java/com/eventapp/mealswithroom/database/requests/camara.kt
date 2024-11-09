package com.eventapp.mealswithroom.database.requests

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.material.*

@Composable
fun CameraPermissionRequest(onPermissionGranted: @Composable () -> Unit) {
    var hasCameraPermission by remember { mutableStateOf(false) }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
        if (isGranted) {
            onPermissionGranted()
        } else {
            // Manejo si se deniega el permiso
        }
    }

    LaunchedEffect(Unit) {
        cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
    }

    if (hasCameraPermission) {
        onPermissionGranted()
    }
}
