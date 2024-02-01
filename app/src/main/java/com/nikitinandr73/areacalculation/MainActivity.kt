//version 1.0.1
package com.nikitinandr73.areacalculation

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.nikitinandr73.areacalculation.ui.theme.AreaCalculationTheme
import com.nikitinandr73.areacalculation.ui.theme.Cmania2
import com.nikitinandr73.areacalculation.ui.theme.LaytBG
import com.nikitinandr73.areacalculation.ui.theme.LaytBG2


const val AD_ID = "ca-app-pub-4378016375488689/2184600588"

class MainActivity : ComponentActivity() {
    private var rewardedAd: RewardedAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        loadAd()

        setContent {
            AreaCalculationTheme {
                val context = LocalContext.current as Activity
                fun handButtonClicked() {
                    rewardedAd?.show(this@MainActivity) { item ->
                        Toast.makeText(
                            this@MainActivity,
                            "Reward: ${item.amount}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    context.recreate()
                }
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
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    MyTopAppBar()
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = stringResource(id = R.string.U_r),
                        fontSize = 22.sp,
                        color = LaytBG,
                        fontFamily = FontFamily.Serif,
                        textDecoration = TextDecoration.Underline
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        MainCalculation()

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Cmania2, contentColor = LaytBG2
                            ), onClick = {
                                handButtonClicked()
                            }) {
                            Text(
                                text = stringResource(id = R.string.S_i_n_n_R),
                                fontSize = 20.sp
                            )

                        }

                    }
                    Box(
                        contentAlignment = Alignment.BottomCenter,
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Spacer(modifier = Modifier.height(20.dp))
                            AdMobBanner(id = R.string.banner_ad_mob1)
                            AdMobBanner(id = R.string.banner_ad_mob2)
                        }
                    }
                }

            }

        }

    }

    private fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        RewardedAd.load(this, AD_ID, adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
            }

            override fun onAdLoaded(ad: RewardedAd) {
                super.onAdLoaded(ad)
                rewardedAd = ad
                rewardedAd?.fullScreenContentCallback = adListener()
            }
        })
    }

    private fun adListener() = object : FullScreenContentCallback() {
        // Если закрыта крестиком, то обнуляем и грузим новую
        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
            rewardedAd = null
            loadAd()
        }

        // Если ошибка была при отображении рекламы, то обнуляем и грузим новую
        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            super.onAdFailedToShowFullScreenContent(p0)
            rewardedAd = null
            loadAd()
        }
    }
}

