package com.appsv.healthify.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.appsv.healthify.domain.SleepDisorderData

@Composable
fun SleepDisorderDialog(
    onDismiss: () -> Unit,
    onSubmit: (SleepDisorderData) -> Unit
) {
    var age by remember { mutableStateOf(60) }
    var bmiCategory by remember { mutableStateOf("Overweight") }
    var occupation by remember { mutableStateOf("Doctor") }
    var gender by remember { mutableStateOf("Female") }
    var bloodPressure by remember { mutableStateOf("130/85") }
    var heartRate by remember { mutableStateOf(88) }
    var stressLevel by remember { mutableStateOf(8) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val data = SleepDisorderData(
                    age = age,
                    bmiCategory = bmiCategory,
                    occupation = occupation,
                    gender = gender,
                    bloodPressure = bloodPressure,
                    heartRate = heartRate,
                    stressLevel = stressLevel
                )
                onSubmit(data)
            }) {
                Text("Submit")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Sleep Disorder Prediction") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    value = age.toString(),
                    onValueChange = { age = it.toIntOrNull() ?: age },
                    label = { Text("Age") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = bmiCategory,
                    onValueChange = { bmiCategory = it },
                    label = { Text("BMI Category") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = occupation,
                    onValueChange = { occupation = it },
                    label = { Text("Occupation") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = gender,
                    onValueChange = { gender = it },
                    label = { Text("Gender") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = bloodPressure,
                    onValueChange = { bloodPressure = it },
                    label = { Text("Blood Pressure") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = heartRate.toString(),
                    onValueChange = { heartRate = it.toIntOrNull() ?: heartRate },
                    label = { Text("Heart Rate") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = stressLevel.toString(),
                    onValueChange = { stressLevel = it.toIntOrNull() ?: stressLevel },
                    label = { Text("Stress Level") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}
