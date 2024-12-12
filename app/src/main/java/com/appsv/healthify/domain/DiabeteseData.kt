package com.appsv.healthify.domain

import com.google.gson.annotations.SerializedName

data class DiabeteseData(
    @SerializedName("Age")
    val age: Int,

    @SerializedName("BMI Category")
    val bmiCategory: String,

    @SerializedName("Occupation")
    val occupation: String?,

    @SerializedName("Gender")
    val gender: String,

    @SerializedName("Systolic")
    val systolic: Int,

    @SerializedName("Diastolic")
    val diastolic: Int,

    @SerializedName("Heart Rate")
    val heartRate: Int,

    @SerializedName("Stress Level")
    val stressLevel: Int,

    @SerializedName("Daily Steps")
    val dailySteps: Int
)
