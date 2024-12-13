import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsv.healthify.Resource
import com.appsv.healthify.domain.DiabeteseData
import com.appsv.healthify.domain.HeartDiseaseData
import com.appsv.healthify.domain.SleepDisorderData
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DiabetesViewModel : ViewModel() {
    private val repository = DiabetesRepository()

    private val _diabetesState = MutableStateFlow<Resource<String>>(Resource.Loading())
    val diabetesState: StateFlow<Resource<String>> = _diabetesState

    private val _heartDiseaseState = MutableStateFlow<Resource<String>>(Resource.Loading())
    val heartDiseaseState: StateFlow<Resource<String>> = _heartDiseaseState

    fun resetState() {
        _diabetesState.value = Resource.Loading()
    }
    fun predictDiabetes(data: DiabeteseData) {
        Log.d("Disease" , "cliekc")

        viewModelScope.launch {
            repository.predictDiabetes(data)
                .onStart { _diabetesState.value = Resource.Loading() }
                .catch { e -> _diabetesState.value = Resource.Error("Error occurred: ${e.message}") }
                .collect { response ->
                    if (response.isSuccessful) {
                        val diabetes = response.body()?.get("Diabetes") ?: 0
                        if (diabetes == 0) {
                            Log.d("Disease" , "Noooo")
                            _diabetesState.value = Resource.Success("No disease, you're good!")
                        } else {
                            Log.d("Disease" , "Yes")
                            _diabetesState.value = Resource.Success("There's a chance of diabetes.")
                        }
                    } else {
                        Log.d("Disease" , "${response.message()} failed")

                        _diabetesState.value = Resource.Error("${response.message()}Failed to fetch data")
                    }
                }
        }
    }

    fun predictHeartDisease(data: HeartDiseaseData) {
        viewModelScope.launch {
            repository.predictHeartDisease(data)
                .onStart { _diabetesState.value = Resource.Loading() }
                .catch { e -> _diabetesState.value = Resource.Error("Error occurred: ${e.message}") }
                .collect { response ->
                    if (response.isSuccessful) {
                        val heartDisease = response.body()?.get("Heart Disease")?.toString() ?: 0
                        if (heartDisease == "0") {
                            _diabetesState.value = Resource.Success("No heart disease detected!")
                        } else {
                            _diabetesState.value = Resource.Success("There's a chance of heart disease.")
                        }
                    } else {
                        _diabetesState.value = Resource.Error("Error: ${response.message()} - Failed to fetch data")
                    }
                }
        }
    }

    fun predictSleepDisorder(data: SleepDisorderData) {
        viewModelScope.launch {
            repository.predictSleepDisorder(data)
                .onStart { _diabetesState.value = Resource.Loading() }
                .catch { e -> _diabetesState.value = Resource.Error("Error occurred: ${e.message}") }
                .collect { response ->
                    Log.d("Respondfasdfseeeeee", response.toString())
                    if (response.isSuccessful) {
                        val sleepDisorder = response.body()?.get("Sleep Disorder") ?: 0
                        if (sleepDisorder == 0) {
                            _diabetesState.value = Resource.Success("No sleep disorder detected!")
                        } else {
                            _diabetesState.value = Resource.Success("There's a chance of sleep disorder.")
                        }
                    } else {
                        _diabetesState.value = Resource.Error("Error: ${response.message()} - Failed to fetch data")
                    }
                }
        }
    }
}
