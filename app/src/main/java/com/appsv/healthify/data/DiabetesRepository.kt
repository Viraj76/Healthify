import com.appsv.healthify.data.RetrofitInstance
import com.appsv.healthify.domain.DiabeteseData
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import android.util.Log
import com.appsv.healthify.data.RetrofitInstance.apiServiceHeartDisease
import com.appsv.healthify.data.RetrofitInstance.apiServiceSleepDisorder
import com.appsv.healthify.domain.HeartDiseaseData
import com.appsv.healthify.domain.SleepDisorderData

class DiabetesRepository {
    private val apiService = RetrofitInstance.apiService

    fun predictDiabetes(data: DiabeteseData) = flow {
        val response = apiService.predictDiabetes(data)
        emit(response)
    }

    fun predictHeartDisease(data: HeartDiseaseData) = flow {
        try {
            val response = apiServiceHeartDisease.predictHeartDisease(data)
            Log.d("Disease" , "YEHD $response ")
            emit(response)
        } catch (e: Exception) {
            Log.d("Disease" , "$e")
            emit(Response.error<Map<String, Int>>(500, okhttp3.ResponseBody.create(null, "")))
        }
    }

    fun predictSleepDisorder(data: SleepDisorderData) = flow {
        try {
            val response = apiServiceSleepDisorder.predictSleepDisorder(data)
            emit(response)
        } catch (e: Exception) {
            emit(Response.error<Map<String, Int>>(500, okhttp3.ResponseBody.create(null, "")))
        }
    }
}
