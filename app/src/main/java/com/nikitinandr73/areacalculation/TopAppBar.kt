package com.nikitinandr73.areacalculation

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.nikitinandr73.areacalculation.ui.theme.LaytBG

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    val contextForToast = LocalContext.current.applicationContext
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.C_p_c_d)) },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(contextForToast, "Test", Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            IconButton(onClick = {val activity: MainActivity = MainActivity()
                activity.finish()
                System.exit(0)
                Toast.makeText(contextForToast, "Add Click", Toast.LENGTH_SHORT).show()
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