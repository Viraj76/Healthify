package com.appsv.healthify.data

import com.appsv.healthify.domain.DiabeteseData
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
