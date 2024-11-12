package com.eventapp.mealswithroom.ui.testui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.concurrent.Executor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CameraScreen() {
    val context = LocalContext.current
    val outputDirectory = context.cacheDir // Cambia esto al directorio donde deseas guardar la imagen
    val executor = ContextCompat.getMainExecutor(context)

    // Usar mutableStateOf para mantener la referencia de ImageCapture
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }

    // Solicitar permisos de cámara
    CameraPermissionRequest {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        imageCapture?.let { capture ->
                            capturePhoto(context, capture, outputDirectory, executor) { file ->
                                Log.d("CameraScreen", "Imagen guardada en ${file.absolutePath}")
                            }
                        } ?: Log.e("CameraScreen", "ImageCapture no está inicializado")
                    }
                ) {
                    Icon(Icons.Default.Share, contentDescription = "Capturar")
                }
            }
        ) {
            CameraPreview(
                modifier = Modifier.fillMaxSize(),
                outputDirectory = outputDirectory,
                executor = executor,
                onImageCaptured = { file ->
                    Log.d("CameraScreen", "Imagen guardada en ${file.absolutePath}")
                },
                onError = { exception ->
                    Log.e("CameraScreen", "Error al capturar la imagen", exception)
                },
                onImageCaptureReady = { capture ->
                    imageCapture = capture
                }
            )
        }
    }
}

@Composable
fun CameraPermissionRequest(onPermissionGranted: @Composable () -> Unit) {
    var hasCameraPermission by remember { mutableStateOf(false) }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasCameraPermission = isGranted
        if (isGranted) {
           // onPermissionGranted()
        } else {
            Log.e("CameraPermissionRequest", "Permiso de cámara denegado")
        }
    }

    LaunchedEffect(Unit) {
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    if (hasCameraPermission) {
        onPermissionGranted()
    }
}

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    outputDirectory: File,
    executor: Executor,
    onImageCaptured: (File) -> Unit,
    onError: (ImageCaptureException) -> Unit,
    onImageCaptureReady: (ImageCapture) -> Unit // Callback para pasar ImageCapture
) {
    var previewView by remember { mutableStateOf<PreviewView?>(null) }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    CameraPermissionRequest {
        AndroidView(
            factory = { context ->
                PreviewView(context).apply {
                    previewView = this
                }
            },
            modifier = modifier
        )

        LaunchedEffect(previewView) {
            val cameraProvider = withContext(Dispatchers.IO) {
                ProcessCameraProvider.getInstance(context).get()
            }

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView?.surfaceProvider)
            }

            val imageCapture = ImageCapture.Builder().build()
            onImageCaptureReady(imageCapture) // Pasar ImageCapture al callback

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner, cameraSelector, preview, imageCapture
                )
            } catch (exc: Exception) {
                Log.e("CameraPreview", "Error al inicializar la cámara", exc)
            }
        }
    }
}

fun capturePhoto(
    context: Context,
    imageCapture: ImageCapture,
    outputDirectory: File,
    executor: Executor,
    onImageCaptured: (File) -> Unit
) {
    val photoFile = File(
        outputDirectory,
        "JPEG_${System.currentTimeMillis()}.jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(
        outputOptions,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exc: ImageCaptureException) {
                Log.e("CameraCapture", "Error al capturar la imagen: ${exc.message}", exc)
            }

            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                onImageCaptured(photoFile)
            }
        }
    )
}
