package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun LoadingScreen(
    onLoadingFinished: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(2500.milliseconds)
        onLoadingFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 90.dp)
        ) {

            Text(
                text = "DriveNext",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF25134F)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Поможем найти твою следующую поездку",
                fontSize = 16.sp,
                color = Color(0xFF777777)
            )
        }

        Image(
            painter = painterResource(
                id = R.drawable.loading_illustration
            ),
            contentDescription = "DriveNext",
            modifier = Modifier
                .size(330.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.Fit
        )
    }
}