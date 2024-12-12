package com.appsv.healthify.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.appsv.healthify.domain.HealthData


@Composable
fun HealthDataDialog(
    onDismiss: () -> Unit,
    onSubmit: (HealthData) -> Unit
) {
    var age by remember { mutableStateOf("30") }
    var bmiCategory by remember { mutableStateOf("Normal") }
    var occupation by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var systolic by remember { mutableStateOf("120") }
    var diastolic by remember { mutableStateOf("80") }
    var heartRate by remember { mutableStateOf("72") }
    var stressLevel by remember { mutableStateOf("3") }
    var dailySteps by remember { mutableStateOf("8000") }
    var errorMessage by remember { mutableStateOf("") }


    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = age,
                    onValueChange = { age = it },
                    label = { Text("Age") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = bmiCategory,
                    onValueChange = { bmiCategory = it },
                    label = { Text("BMI Category") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = occupation,
                    onValueChange = { occupation = it },
                    label = { Text("Occupation (Optional)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = gender,
                    onValueChange = { gender = it },
                    label = { Text("Gender") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = systolic,
                    onValueChange = { systolic = it },
                    label = { Text("Systolic") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = diastolic,
                    onValueChange = { diastolic = it },
                    label = { Text("Diastolic") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = heartRate,
                    onValueChange = { heartRate = it },
                    label = { Text("Heart Rate") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = stressLevel,
                    onValueChange = { stressLevel = it },
                    label = { Text("Stress Level") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = dailySteps,
                    onValueChange = { dailySteps = it },
                    label = { Text("Daily Steps") },
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        if (age.isBlank() || bmiCategory.isBlank() || gender.isBlank()
                            || systolic.isBlank() || diastolic.isBlank()
                            || heartRate.isBlank() || stressLevel.isBlank() || dailySteps.isBlank()
                        ) {
                            errorMessage = "Please fill all required fields"
                        } else {
                            val healthData = HealthData(
                                age = age.toInt(),
                                bmiCategory = bmiCategory,
                                occupation = occupation.ifBlank { null },
                                gender = gender,
                                systolic = systolic.toInt(),
                                diastolic = diastolic.toInt(),
                                heartRate = heartRate.toInt(),
                                stressLevel = stressLevel.toInt(),
                                dailySteps = dailySteps.toInt()
                            )
                            onSubmit(healthData)
                            onDismiss()
                        }
                    }) {
                        Text("Submit")
                    }
                }
            }
        }
    }
}
