package com.nikitinandr73.areacalculation

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.nikitinandr73.areacalculation.ui.theme.LaytBG
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    val navigationState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.C_p_c_d)) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {navigationState.open()}
            }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "Setting_icon")
            }
        },
        actions = {
            IconButton(onClick = {val activity: MainActivity = MainActivity()
                activity.finish()
                System.exit(0)
            }) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.Red)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = LaytBG.copy(alpha = 0.3f)
        )
    )
}