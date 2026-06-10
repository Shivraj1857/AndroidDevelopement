import android.content.Context
import androidx.lifecycle.ViewModel
import io.mastercoding.fakeblecentral.data.BleCentralRepository
import io.mastercoding.fakebleperipheral.ui.BleUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BleViewModel : ViewModel() {

    private lateinit var repository: BleCentralRepository

    private val _uiState = MutableStateFlow(BleUiState())
    val uiState = _uiState.asStateFlow()

    fun startConnection(context: Context) {

        repository = BleCentralRepository(context)

        repository.connectAndRead { value ->

            // SIMPLE FIX: better parsing
            when {
                value.contains("CONNECTED") -> {
                    _uiState.value = _uiState.value.copy(
                        connectionState = "Connected"
                    )
                }

                value.contains("DISCONNECTED") -> {
                    _uiState.value = _uiState.value.copy(
                        connectionState = "Disconnected"
                    )
                }

                else -> {
                    _uiState.value = _uiState.value.copy(
                        temperature = value
                    )
                }
            }
        }
    }
}