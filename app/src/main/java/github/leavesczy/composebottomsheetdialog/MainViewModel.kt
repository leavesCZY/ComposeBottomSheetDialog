package github.leavesczy.composebottomsheetdialog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * @Author: leavesCZY
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class MainViewModel : ViewModel() {

    var viewState by mutableStateOf(
        value = ViewState(
            visible = false,
            onShowRequest = ::onShowRequest,
            onDismissRequest = ::onDismissRequest
        )
    )
        private set

    private fun onShowRequest() {
        viewState = viewState.copy(visible = true)
    }

    private fun onDismissRequest() {
        viewState = viewState.copy(visible = false)
    }

}