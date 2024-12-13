package com.appsv.healthify.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://diabetes-disease-ml-model.onrender.com"
    private const val HEART_DISEASE_BASE_URL = "https://heart-disease-ml-model-2.onrender.com"
    private const val BASE_URL_SLEEP_ORDER = "https://sleeping-disorder-ml-model-1.onrender.com"


    val apiService: DiabetesApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiabetesApiService::class.java)
    }

    val apiServiceHeartDisease: HeartDiseaseApiService by lazy {
        Retrofit.Builder()
            .baseUrl(HEART_DISEASE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeartDiseaseApiService::class.java)
    }


    val apiServiceSleepDisorder: SleepDisorderApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_SLEEP_ORDER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SleepDisorderApiService::class.java)
    }
}
