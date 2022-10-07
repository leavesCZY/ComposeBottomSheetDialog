package github.leavesczy.composebottomsheetdialog

/**
 * @Author: leavesCZY
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
data class ViewState(
    val visible: Boolean,
    val onShowRequest: () -> Unit,
    val onDismissRequest: () -> Unit
)