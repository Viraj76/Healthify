package com.appsv.healthify.presentation

import DiabetesViewModel
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
import com.appsv.healthify.presentation.components.HeartDiseaseDialog
import com.appsv.healthify.presentation.components.SleepDisorderDialog

@Composable
fun HeartScreen(
    viewModel: DiabetesViewModel,
    diabetesState: Resource<String>,
    innerPadding: PaddingValues,
) {
    var showDialog by remember { mutableStateOf(false) }
    var showHeartDiseaseDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
    var showSleepDisorderDialog by remember { mutableStateOf(false) }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = {
                showSuccessDialog = false
                viewModel.resetState()
            },
            title = { Text(text = "Prediction Result") },
            text = { Text(dialogMessage) },
            confirmButton = {
                TextButton(onClick = {
                    showSuccessDialog = false
                    viewModel.resetState()
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
            showSuccessDialog = false
            dialogMessage = diabetesState.message
        }
        is Resource.Loading -> {
            showSuccessDialog = false
            dialogMessage = "Loading..."
        }
    }


    if (showDialog) {
        HealthDataDialog(
            onDismiss = { showDialog = false },
            onSubmit = { healthData ->
                viewModel.predictDiabetes(healthData)
                showDialog = false
            }
        )
    }

    if (showHeartDiseaseDialog) {
        HeartDiseaseDialog(
            onDismiss = { showHeartDiseaseDialog = false },
            onSubmit = { heartDiseaseData ->
                viewModel.predictHeartDisease(heartDiseaseData)
                showHeartDiseaseDialog = false
            }
        )
    }

    if(showSleepDisorderDialog){
        SleepDisorderDialog(
            onDismiss = {
                showSleepDisorderDialog = false
            },
            onSubmit = {
                viewModel.predictSleepDisorder(it)
                showSleepDisorderDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFff9a9e),
                        Color(0xFFfad0c4)
                    )
                )
            )
            .padding(innerPadding)
            .padding(16.dp)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Predict Disease",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp)
        )

        GradientButton(
            text = "Predict Diabetes Disease",
            onClick = { showDialog = true }
        )
        Spacer(modifier = Modifier.height(16.dp))
        GradientButton(
            text = "Predict Heart Disease",
            onClick = { showHeartDiseaseDialog = true }
        )
        Spacer(modifier = Modifier.height(16.dp))
        GradientButton(
            text = "Predict Sleep Disorder",
            onClick = {
                showSleepDisorderDialog = true
            }
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
