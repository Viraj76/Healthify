import com.appsv.healthify.data.RetrofitInstance
import com.appsv.healthify.domain.DiabeteseData
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import android.util.Log

class DiabetesRepository {
    private val apiService = RetrofitInstance.apiService

    fun predictDiabetes(data: DiabeteseData) = flow {
        val response = apiService.predictDiabetes(data)
        Log.d("Disease" , "$response")
        emit(response)
    }
}
