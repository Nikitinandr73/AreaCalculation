package com.nikitinandr73.areacalculation

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikitinandr73.areacalculation.ui.theme.Cmania2
import com.nikitinandr73.areacalculation.ui.theme.LaytBG
import com.nikitinandr73.areacalculation.ui.theme.LaytBG2

@Composable
fun Screen_1() {
    Image(
        painter = painterResource(id = R.drawable.b_bg),
        contentDescription = "dark_bg",
        modifier = Modifier
            .fillMaxSize()
            .alpha(1f),
        contentScale = ContentScale.FillBounds
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        MyTopAppBar()
        Text(
            modifier = Modifier.padding(10.dp),
            text = stringResource(id = R.string.U_r),
            fontSize = 22.sp,
            color = LaytBG,
            fontFamily= FontFamily.Serif,
            textDecoration = TextDecoration.Underline
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            MainCalculation()
            val context = LocalContext.current as Activity
            Button(
                modifier = Modifier.fillMaxWidth().
                padding(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Cmania2, contentColor = LaytBG2
                ),onClick = {
                    context.recreate()
                }) {
                Text(
                    text = stringResource(id = R.string.S_i_n_n_R),
                    fontSize = 20.sp
                )
            }
        }
    }

}