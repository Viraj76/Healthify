package com.appsv.healthify.domain

import com.google.gson.annotations.SerializedName

data class SleepDisorderData(
    @SerializedName("Age") val age: Int,
    @SerializedName("BMI Category") val bmiCategory: String,
    @SerializedName("Occupation") val occupation: String?,
    @SerializedName("Gender") val gender: String,
    @SerializedName("Blood Pressure") val bloodPressure: String,
    @SerializedName("Heart Rate") val heartRate: Int,
    @SerializedName("Stress Level") val stressLevel: Int
)
