import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsv.healthify.Resource
import com.appsv.healthify.domain.DiabeteseData
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.launch

class DiabetesViewModel : ViewModel() {
    private val repository = DiabetesRepository()

    private val _diabetesState = MutableStateFlow<Resource<String>>(Resource.Loading())
    val diabetesState: StateFlow<Resource<String>> = _diabetesState

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
}
