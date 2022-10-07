package github.leavesczy.composebottomsheetdialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @Author: leavesCZY
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class MainViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(
        ViewState(
            visible = false,
            onShowRequest = ::onShowRequest,
            onDismissRequest = ::onDismissRequest
        )
    )

    val viewState: StateFlow<ViewState> = _viewState

    private fun onShowRequest() {
        viewModelScope.launch {
            _viewState.emit(value = _viewState.value.copy(visible = true))
        }
    }

    private fun onDismissRequest() {
        viewModelScope.launch {
            _viewState.emit(value = _viewState.value.copy(visible = false))
        }
    }

}