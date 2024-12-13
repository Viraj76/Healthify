package com.appsv.healthify.data

import com.appsv.healthify.domain.DiabeteseData
import com.appsv.healthify.domain.HeartDiseaseData
import com.appsv.healthify.domain.SleepDisorderData
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Headers

interface DiabetesApiService {
    @Headers("Content-Type: application/json")
    @POST("/predict")
   suspend fun predictDiabetes(@Body data: DiabeteseData): Response<Map<String, Int>>
}

interface HeartDiseaseApiService{
    @Headers("Content-Type: application/json")
    @POST("/predict")
    suspend fun predictHeartDisease(@Body data: HeartDiseaseData): Response<Map<String, Int>>
}

interface SleepDisorderApiService {
    @Headers("Content-Type: application/json")
    @POST("/predict")
    suspend fun predictSleepDisorder(@Body data: SleepDisorderData): Response<Map<String, Int>>
}
