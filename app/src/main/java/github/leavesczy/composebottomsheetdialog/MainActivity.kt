package github.leavesczy.composebottomsheetdialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import github.leavesczy.composebottomsheetdialog.ui.theme.ComposeBottomSheetDialogTheme

/**
 * @Author: leavesCZY
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    private val dialogContentBackground = Color(0xFFFFEB3B)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ComposeBottomSheetDialogTheme {
                val viewState = mainViewModel.viewState
                val systemUiController = rememberSystemUiController()
                systemUiController.setNavigationBarColor(
                    color = if (viewState.visible) {
                        dialogContentBackground
                    } else {
                        MaterialTheme.colorScheme.background
                    },
                    darkIcons = true
                )
                Scaffold(
                    modifier = Modifier,
                    contentWindowInsets = WindowInsets.navigationBars
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = innerPadding)
                    ) {
                        Button(
                            modifier = Modifier
                                .align(alignment = Alignment.Center),
                            onClick = viewState.onShowRequest
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(all = 4.dp),
                                text = "showDialog",
                                fontSize = 16.sp
                            )
                        }
                        BottomSheetDialog(
                            modifier = Modifier,
                            visible = viewState.visible,
                            cancelable = false,
                            canceledOnTouchOutside = false,
                            onDismissRequest = viewState.onDismissRequest
                        ) {
                            DialogContent(onDismissRequest = viewState.onDismissRequest)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun DialogContent(onDismissRequest: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.7f)
                .background(color = dialogContentBackground),
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                onClick = onDismissRequest
            ) {
                Text(
                    modifier = Modifier.padding(all = 4.dp),
                    text = "dismissDialog",
                    fontSize = 16.sp
                )
            }
        }
    }

}