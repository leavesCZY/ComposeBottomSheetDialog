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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import github.leavesczy.composebottomsheetdialog.ui.theme.ComposeBottomSheetDialogTheme

/**
 * @Author: leavesCZY
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val viewState by mainViewModel.viewState.collectAsState()
            ComposeBottomSheetDialogTheme {
                Scaffold(
                    modifier = Modifier,
                    contentWindowInsets = WindowInsets.navigationBars
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = innerPadding)
                    ) {
                        Button(modifier = Modifier
                            .align(alignment = Alignment.Center),
                            onClick = {
                                viewState.onShowRequest()
                            }) {
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
                            cancelable = true,
                            canceledOnTouchOutside = true,
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
                .clip(shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .background(color = Color(0xFF009688)),
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                onClick = {
                    onDismissRequest()
                }) {
                Text(
                    modifier = Modifier.padding(all = 4.dp),
                    text = "dismissDialog",
                    fontSize = 16.sp
                )
            }
        }
    }

}