package com.appsv.healthify.presentation

import DiabetesViewModel
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appsv.healthify.Resource
import com.appsv.healthify.presentation.components.HealthDataDialog

@Composable
fun HeartScreen(
    viewModel: DiabetesViewModel,
    diabetesState: Resource<String>,
) {
    var showDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = {
                showSuccessDialog = false
                viewModel.resetState() // Reset state in ViewModel
            },
            title = { Text(text = "Prediction Result") },
            text = { Text(dialogMessage) },
            confirmButton = {
                TextButton(onClick = {
                    showSuccessDialog = false
                    viewModel.resetState() // Reset state in ViewModel
                }) {
                    Text("OK")
                }
            }
        )
    }

    when (diabetesState) {
        is Resource.Success -> {
            if (!showSuccessDialog) {
                showSuccessDialog = true
                dialogMessage = diabetesState.data
            }
        }
        is Resource.Error -> {
            dialogMessage = diabetesState.message ?: "Unknown Error"
        }
        is Resource.Loading -> {
            dialogMessage = "Loading..."
        }
    }

    if (showDialog) {
        HealthDataDialog(
            onDismiss = { showDialog = false },
            onSubmit = { healthData ->
                viewModel.predictDiabetes(healthData)
                showDialog = false
            },
            viewModel = viewModel
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Heart Screen",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 32.dp)
        )

        GradientButton(
            text = "Open Dialog",
            onClick = { showDialog = true }
        )
        Spacer(modifier = Modifier.height(16.dp))
        GradientButton(
            text = "Button 2",
            onClick = { println("Button 2 clicked") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        GradientButton(
            text = "Button 3",
            onClick = { println("Button 3 clicked") }
        )
    }
}


@Composable
fun GradientButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFe52d27), Color(0xFFb31217))
                    ),
                    shape = CircleShape
                )
                .border(
                    width = 2.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFf4a261), Color(0xFFe76f51))
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
