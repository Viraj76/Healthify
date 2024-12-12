package com.appsv.healthify.domain

data class HealthData(
    val age: Int,
    val bmiCategory: String,
    val occupation: String?,
    val gender: String,
    val systolic: Int,
    val diastolic: Int,
    val heartRate: Int,
    val stressLevel: Int,
    val dailySteps: Int
)